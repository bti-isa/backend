package com.isa.BloodTransferInstitute.dto.address;

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
public class NewAddressDTO {

	@NotBlank
	String city;

	@NotBlank
	String street;

	@NotBlank
	String country;

	@NotNull
	Integer postalCode;

	@NotBlank
	String number;

	@NotNull
	Double longitude;

	@NotNull
	Double latitude;

}
