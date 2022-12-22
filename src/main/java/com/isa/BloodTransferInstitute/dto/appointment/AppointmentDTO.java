package com.isa.BloodTransferInstitute.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.BloodTransferInstitute.dto.ReportDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.PatientDTO;
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

	@JsonFormat(pattern="dd.MM.yyyy. HH:mm")
	LocalDateTime dateTime;

	Double duration;

	PatientDTO patient;

	ReportDTO report;

	AppointmentStatus status;

	BloodBankDTO bloodBank;

}
