package com.isa.BloodTransferInstitute.dto.appointment;

import com.isa.BloodTransferInstitute.dto.NewPollDTO;

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
public class ScheduleAppointmentDTO {

	@NotNull
	Long appointmentId;

	@NotNull
	String username;

	@NotNull
	NewPollDTO poll;
}
