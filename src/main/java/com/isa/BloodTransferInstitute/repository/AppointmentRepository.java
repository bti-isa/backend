package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import com.isa.BloodTransferInstitute.model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByDateTimeAndStatus(LocalDateTime dateTime, AppointmentStatus status, Pageable pageable);

	List<Appointment> findByStatus(AppointmentStatus status);

	List<Appointment> findByBloodBankId(Long bloodBankId);

	List<Appointment> findByBloodBankIdAndStatusAndDateTimeGreaterThanEqual(Long bloodBankId, AppointmentStatus status, LocalDateTime dateTime);
	List<Appointment> findByBloodBankIdAndStatus(Long bloodBankId, AppointmentStatus status);

	List<Appointment> findByPatientId(Long patientId);
}
