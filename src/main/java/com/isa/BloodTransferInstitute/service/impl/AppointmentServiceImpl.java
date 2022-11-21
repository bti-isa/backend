package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.exception.CreateAppointmentException;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.exception.ScheduleException;
import com.isa.BloodTransferInstitute.mappers.AppointmentMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.AppointmentRepository;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public Appointment create(final NewAppointmentDTO appointmentDTO) {
		if(appointmentValidation(appointmentDTO)) {
			throw new  CreateAppointmentException();
		}
		BloodBank bloodBank = bloodBankRepository.findById(appointmentDTO.getBloodBankId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.NewDTOToEntity(appointmentDTO, bloodBank));
	}

	private boolean appointmentValidation(final NewAppointmentDTO appointmentDTO) {
		var notHappenedAppointments = appointmentRepository.findByStatus(AppointmentStatus.AVAILIBLE);
		notHappenedAppointments.addAll(appointmentRepository.findByStatus(AppointmentStatus.SCHEDULED));
		return notHappenedAppointments.stream()
			.anyMatch(appointment -> Objects.equals(appointmentDTO.getBloodBankId(), appointment.getBloodBank().getId()) && appointmentDTO.getDateTime().isEqual(appointment.getDateTime()));
	}

	@Override
	public Appointment schedule(final ScheduleAppointmentDTO dto) {
		if(scheduleValidationForPastSixMonths(dto.getPatientId()) || scheduleValidation(dto.getPatientId())) {
			throw new ScheduleException();
		}
		User patient = userRepository.findByIdAndRole(dto.getPatientId(), Role.PATIENT).orElseThrow(NotFoundException::new);
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.ScheduleDTOToEntity(appointment, patient));
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
	public List<Appointment> findByDateTime(final LocalDateTime dateTime) {
		return appointmentRepository.findByDateTime(dateTime);
	}

	@Override
	public List<Appointment> findAllAvailable(int pageSize, int pageNumber) {
		return appointmentRepository.findByStatus(AppointmentStatus.AVAILIBLE, PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "bloodBank.rating")));
	}

	@Override
	public List<Appointment> findAllCompleted() {
		return appointmentRepository.findByStatus(AppointmentStatus.COMPLETED);
	}

	@Override
	public List<Appointment> findAllScheduled() {
		return appointmentRepository.findByStatus(AppointmentStatus.SCHEDULED);
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
}
