package com.isa.BloodTransferInstitute.dto.appointment;

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
public class UpdateAppointmentDTO {

	Long id;

	LocalDateTime dateTime;

	Long patientId;

	AppointmentStatus status;

	Long bloodBankId;

}
