package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.dto.appointment.mappers.AppointmentMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.AppointmentRepository;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final BloodBankRepository bloodBankRepository;
	private final UserRepository userRepository;

	@Override
	public Appointment create(final NewAppointmentDTO appointmentDTO) {
		BloodBank bloodBank = bloodBankRepository.findById(appointmentDTO.getBloodBankId()).orElseThrow(NotFoundException::new);
		return appointmentRepository.save(AppointmentMapper.NewDTOToEntity(appointmentDTO, bloodBank));
	}

	@Override
	public Appointment schedule(final ScheduleAppointmentDTO dto) {
		User patient = userRepository.findById(dto.getPatientId()).orElseThrow(NotFoundException::new);
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
}
