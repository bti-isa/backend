package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface AppointmentService {

	Appointment create(NewAppointmentDTO appointmentDTO);

	Appointment schedule(ScheduleAppointmentDTO appointmentDTO);

	Appointment finish(FinishedAppointmentDTO appointmentDTO);

	List<Appointment> getAll();

	Optional<Appointment> findById(Long id);

	List<Appointment> findAllAvailableByDateTime(final LocalDateTime dateTime, int pageSize, int pageNumber, Sort.Direction direction);

	List<Appointment> findAllCompleted();

	List<Appointment> findAllScheduled();

	List<Appointment> findAllByPatientId(Long patientId);

	List<Appointment> findAllByAdminsBloodBankId(Long adminsId);

	List<Appointment> findAllByBloodbankId(Long id);
}
