package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.appointment.NewAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.FinishedAppointmentDTO;
import com.isa.BloodTransferInstitute.dto.appointment.ScheduleAppointmentDTO;
import com.isa.BloodTransferInstitute.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

	Appointment create(NewAppointmentDTO appointmentDTO);

	Appointment schedule(ScheduleAppointmentDTO appointmentDTO);

	Appointment finish(FinishedAppointmentDTO appointmentDTO);

	List<Appointment> getAll();

	Optional<Appointment> findById(Long id);

	List<Appointment> findByDateTime(LocalDateTime dateTime);

	List<Appointment> findAllAvailable(int pageSize, int pageNumber);

	List<Appointment> findAllCompleted();

	List<Appointment> findAllScheduled();
}
