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
public class UpdateAddressDTO {

	@NotNull
	Long id;

	@NotBlank
	String city;

	@NotBlank
	String street;

	@NotBlank
	String country;

	@NotBlank
	String number;

	@NotNull
	Integer postalCode;

	@NotNull
	Long locationId;

	@NotNull
	Double longitude;

	@NotNull
	Double latitude;

}
