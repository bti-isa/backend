package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.exception.*;
import com.isa.BloodTransferInstitute.mappers.AppointmentMapper;
import com.isa.BloodTransferInstitute.mappers.PollMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.AppointmentRepository;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.PollRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final BloodBankRepository bloodBankRepository;
	private final UserRepository userRepository;
	private final PollRepository pollRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public Appointment create(final NewAppointmentDTO appointmentDTO) {
		User admin = userRepository.findByUsername(appointmentDTO.getUsername());
		if(appointmentValidation(appointmentDTO, admin)) {
			throw new  CreateAppointmentException();
		}
		BloodBank bloodBank = bloodBankRepository.findById(admin.getBloodBank().getId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.NewDTOToEntity(appointmentDTO, bloodBank));
	}

	private boolean appointmentValidation(final NewAppointmentDTO appointmentDTO, final User admin) {
		var notHappenedAppointments = appointmentRepository.findByStatus(AppointmentStatus.AVAILIBLE);
		notHappenedAppointments.addAll(appointmentRepository.findByStatus(AppointmentStatus.SCHEDULED));
		return notHappenedAppointments.stream()
			.anyMatch(appointment -> Objects.equals(admin.getBloodBank().getId(), appointment.getBloodBank().getId()) && appointmentDTO.getDateTime().isEqual(appointment.getDateTime()));
	}

	@Override
	public Appointment schedule(final ScheduleAppointmentDTO dto) {
		User patient = userRepository.findByUsername(dto.getUsername());
		if(scheduleValidationForPastSixMonths(patient.getId()) || scheduleValidation(patient.getId())) {
			throw new ScheduleException();
		}
		pollRepository.save(PollMapper.NewDTOToEntity(dto.getPoll(), patient));
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow(NotFoundException::new);
		emailSenderService.sendSimpleEmail(dto.getUsername(),"Successfully schedule appointment", "Dear "+patient.getFirstname()+ ", your successfully schedule appointment in "+appointment.getDateTime().toString().replace('T',' ')+" at "+appointment.getBloodBank().getName()+".");
		return appointmentRepository.save(AppointmentMapper.ScheduleDTOToEntity(appointment, patient));
	}

	@Override
	public Appointment cancelAppointment(Long id){
		Appointment appointment = appointmentRepository.findById(id).get();
		if(appointment.getDateTime().compareTo(LocalDateTime.now())<0)
			throw new PastAppointmentException();
		if(appointment.getDateTime().plusDays(1).compareTo(LocalDateTime.now())<0)
			throw new CancelException();
		appointment.setStatus(AppointmentStatus.AVAILIBLE);
		appointment.setPatient(null);
		return appointmentRepository.save(appointment);
	}
	@Override
	public Appointment finish(final FinishedAppointmentDTO appointmentDTO) {
		Appointment appointment = appointmentRepository.findById(appointmentDTO.getId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.FinishDTOToEntity(appointmentDTO, appointment));
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

	public List<Appointment> findAllByBloodbankId(Long id){
		return appointmentRepository.findByBloodBankIdAndStatusAndDateTimeGreaterThanEqual(id,AppointmentStatus.AVAILIBLE, LocalDateTime.now());
	}
}
