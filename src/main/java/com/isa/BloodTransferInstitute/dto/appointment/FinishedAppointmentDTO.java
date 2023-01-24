package com.isa.BloodTransferInstitute.dto.appointment;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class FinishedAppointmentDTO {

	@NotNull
	Long id;

	@NotNull
	Long patientId;

	@NotNull
	Long bloodBankId;

	@NotBlank
	String reportDescription;

	@NotNull
	Integer equipment;

	@NotNull
	Integer bloodQuantity;
}
