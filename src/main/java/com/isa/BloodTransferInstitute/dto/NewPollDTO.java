package com.isa.BloodTransferInstitute.dto;

import javax.persistence.Column;
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
public class NewPollDTO {

	@NotNull
	Boolean weightOver50kg;

	@NotNull
	Boolean commonCold;

	@NotNull
	Boolean skinDiseases;

	@NotNull
	Boolean problemWithPressure;

	@NotNull
	Boolean antibiotics;

	@NotNull
	Boolean menstruation;

	@NotNull
	Boolean dentalIntervention;

	@NotNull
	Boolean other;
}
