package com.isa.BloodTransferInstitute.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class AddressDTO {

	Long id;

	String city;

	String street;

	String country;

	String number;

	Integer postalCode;

	Double longitude;

	Double latitude;

	Long bloodBankId;

}
