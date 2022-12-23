package com.isa.BloodTransferInstitute.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.time.LocalDateTime;

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
public class NewAppointmentDTO {

	@NotNull
	@JsonFormat(pattern="dd.MM.yyyy. HH:mm")
	LocalDateTime dateTime;

	@NotNull
	String username;

}
