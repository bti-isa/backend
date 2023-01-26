package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.exception.*;
import com.isa.BloodTransferInstitute.mappers.AppointmentMapper;
import com.isa.BloodTransferInstitute.mappers.PollMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.BloodUnit;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.*;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.isa.BloodTransferInstitute.service.PatientService;

import javax.persistence.OptimisticLockException;

import org.mapstruct.control.MappingControl.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final BloodBankRepository bloodBankRepository;
	private final UserRepository userRepository;
	private final PollRepository pollRepository;
	private final BloodUnitRepository bloodUnitRepository;

	private final PatientService patientService;

	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE, timeout = 5, readOnly = false)
	public Appointment create(final NewAppointmentDTO appointmentDTO) {
		User admin = userRepository.findByUsername(appointmentDTO.getUsername());
		if(!appointmentValidation(appointmentDTO, admin)){
			throw new  CreateAppointmentException();
		}

		BloodBank bloodBank = bloodBankRepository.findById(admin.getBloodBank().getId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.NewDTOToEntity(appointmentDTO, bloodBank));
	}

	private boolean appointmentValidation(final NewAppointmentDTO appointmentDTO, final User admin) {
//		var notHappenedAppointments = appointmentRepository.findByStatus(AppointmentStatus.AVAILIBLE);
//		notHappenedAppointments.addAll(appointmentRepository.findByStatus(AppointmentStatus.SCHEDULED));
//		return notHappenedAppointments.stream()
//			.anyMatch(appointment -> Objects.equals(admin.getBloodBank().getId(), appointment.getBloodBank().getId()) && appointmentDTO.getDateTime().isEqual(appointment.getDateTime()));
		var appointments = appointmentRepository.findByBloodBankId(admin.getBloodBank().getId());
		for(var appointment : appointments){
			if(appointment.getDateTime().isEqual(appointmentDTO.getDateTime())){
				return false;
			}
		}
		return true;
	}

	@Override
	public void preSchedule(final ScheduleAppointmentDTO dto) {
		User patient = userRepository.findByUsername(dto.getUsername());
		if(patient.getPenalties() > 2){
			throw new PenaltyException();
		}
		if(scheduleValidationForPastSixMonths(patient.getId()) || scheduleValidation(patient.getId())) {
			throw new ScheduleException();
		}
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow(NotFoundException::new);
		BloodBank bloodBank = appointment.getBloodBank();
		pollRepository.save(PollMapper.NewDTOToEntity(dto.getPoll(), patient));
		emailSenderService.sendEmailWithQRCode(dto.getUsername(),"Appointment information", bloodBank.getName() + "\n" +
			bloodBank.getAddress().getStreet() + " " + bloodBank.getAddress().getNumber() + "\n" +
			bloodBank.getAddress().getCity() + ", " + bloodBank.getAddress().getCountry() + "\n" +
			bloodBank.getAddress().getPostalCode(), appointment.getId(), patient.getId());
	}

	@Override
	@Transactional
	public Appointment schedule(final Long appointmentId, final Long patientId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(NotFoundException::new);
		User patient = userRepository.findById(patientId).orElseThrow(NotFoundException::new);
		try {
			return appointmentRepository.save(AppointmentMapper.ScheduleDTOToEntity(appointment, patient));
		} catch (OptimisticLockException e) {
			throw new ScheduleException();
		}
	}

	@Override
	public Appointment cancelAppointment(Long id){
		Appointment appointment = appointmentRepository.findById(id).get();
		if(appointment.getDateTime().compareTo(LocalDateTime.now())<0)
			throw new PastAppointmentException();
		if(appointment.getDateTime().isBefore(LocalDateTime.now().plusDays(1)))
			throw new CancelException();
		patientService.punish(appointment.getPatient());
		appointment.setStatus(AppointmentStatus.AVAILIBLE);
		appointment.setPatient(null);
		return appointmentRepository.save(appointment);
	}
	@Override
	public Appointment finish(final FinishedAppointmentDTO appointmentDTO) {
		Appointment appointment = appointmentRepository.findById(appointmentDTO.getId()).orElseThrow(NotFoundException::new);
		if(!handleEquipment(appointmentDTO.getEquipment(),appointment.getBloodBank().getId()))
			throw new NoEquipmentException();

		handleBloodUnits(appointmentDTO.getBloodQuantity(),appointment);
		return appointmentRepository.save(AppointmentMapper.FinishDTOToEntity(appointmentDTO, appointment));
	}

	private boolean handleEquipment(Integer equipment, Long bloodBankId){
		BloodBank bloodBank = bloodBankRepository.findById(bloodBankId).orElseThrow(NotFoundException::new);
		if(bloodBank.getEquipment() < equipment)
			return false;

		bloodBank.setEquipment(bloodBank.getEquipment()-equipment);
		bloodBankRepository.save(bloodBank);
		return true;
	}

	private void handleBloodUnits(Integer bloodQuantity, Appointment appointment){
		BloodType patientBloodType = appointment.getPatient().getBloodType();
		var bloodBankId = appointment.getBloodBank().getId();
		List<BloodUnit> bloodUnits = bloodUnitRepository.getByBankAndBloodType(bloodBankId,patientBloodType);

		if(bloodUnits.isEmpty()) {
			createNewBloodUnit(bloodQuantity, patientBloodType, bloodBankId);
			return;
		}

		increaseBloodQuantity(bloodQuantity,bloodUnits,patientBloodType);
	}

	private void increaseBloodQuantity(Integer bloodQuaintity, List<BloodUnit> bloodUnits, BloodType patientBloodType){
		for(BloodUnit unit: bloodUnits){
			if(unit.getBloodType().equals(patientBloodType)){
				unit.setQuantity(unit.getQuantity()+bloodQuaintity);
				bloodUnitRepository.save(unit);
			}
		}
	}

	private void createNewBloodUnit(Integer bloodQuantity, BloodType patientBloodType, Long bloodBankId){
		var newUnit = new BloodUnit(bloodQuantity,patientBloodType);
		newUnit.setBloodBank(bloodBankRepository.findById(bloodBankId).get());
		bloodUnitRepository.save(newUnit);
	}

	@Override
	public List<Appointment> getAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public Optional<Appointment> findById(final Long id) {
		return appointmentRepository.findById(id);
	}

	@Override
	public List<Appointment> findAllAvailableByDateTime(final LocalDateTime dateTime, int pageSize, int pageNumber, Sort.Direction direction) {
		return appointmentRepository.findByDateTimeAndStatus(dateTime, AppointmentStatus.AVAILIBLE, PageRequest.of(pageNumber, pageSize, Sort.by(direction, "bloodBank.rating")));
	}

	@Override
	public List<Appointment> findAllCompleted() {
		return appointmentRepository.findByStatus(AppointmentStatus.COMPLETED);
	}

	@Override
	public List<Appointment> findAllScheduled() {
		return appointmentRepository.findByStatus(AppointmentStatus.SCHEDULED);
	}

	@Override
	public List<Appointment> findAllByPatientId(Long patientId) {
		return appointmentRepository.findByPatientId(patientId);
	}

	@Override
	public List<Appointment> findAllByAdminsUsername(String username) {
		User instituteAdmin = userRepository.findByUsername(username);
		return appointmentRepository.findByBloodBankId(instituteAdmin.getBloodBank().getId());
	}

	private boolean scheduleValidationForPastSixMonths(Long patientId) {
		final var completedAppointmentsOfPatient = getPatientsCompletedAppointments(patientId);
		final var sixMonths = 6;
		return completedAppointmentsOfPatient.stream()
			.anyMatch(appointment -> appointment.getDateTime().isAfter(LocalDateTime.now().minusMonths(sixMonths)));
	}

	private List<Appointment> getPatientsCompletedAppointments(Long patientId) {
		return findAllCompleted().stream()
			.filter(appointment -> Objects.equals(patientId, appointment.getPatient().getId()))
			.toList();
	}

	private List<Appointment> getPatientScheduledAppointments(Long patientId) {
		return findAllScheduled().stream()
			.filter(appointment -> Objects.equals(patientId, appointment.getPatient().getId()))
			.toList();
	}

	private boolean scheduleValidation(Long patientId) {
		return !getPatientScheduledAppointments(patientId).isEmpty();
	}

	private boolean appointmentRecentlyScheduled(Long appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(ScheduleException::new);
		return appointment.getStatus() != AppointmentStatus.AVAILIBLE;
	}

	public List<Appointment> findAllByBloodbankId(Long id){
		return appointmentRepository.findByBloodBankIdAndStatusAndDateTimeGreaterThanEqual(id,AppointmentStatus.AVAILIBLE, LocalDateTime.now());
	}
	public List<Appointment> findAllFinishedByBloodbankId(Long id){
		return appointmentRepository.findByBloodBankIdAndStatus(id,AppointmentStatus.COMPLETED);
	}
}
