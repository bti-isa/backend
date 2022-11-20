package com.isa.BloodTransferInstitute.repository.dto.appointment;

import com.isa.BloodTransferInstitute.repository.dto.ReportDTO;
import com.isa.BloodTransferInstitute.repository.dto.user.patient.PatientDTO;
import com.isa.BloodTransferInstitute.enums.AppointmentStatus;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentDTO {

	Long id;

	LocalDateTime dateTime;

	Double duration;

	PatientDTO patient;

	ReportDTO reportDTO;

	AppointmentStatus status;

	Long bloodBankId;
}
