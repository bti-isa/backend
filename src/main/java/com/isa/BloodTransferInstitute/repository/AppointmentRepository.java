package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDateTime(LocalDateTime dateTime);

	List<Appointment> findByStatus(AppointmentStatus status);
}
