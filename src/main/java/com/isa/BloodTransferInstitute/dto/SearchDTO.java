package com.isa.BloodTransferInstitute.dto;

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
public class SearchDTO {

//	int pageSize;
//
//	int pageNumber;

	String name;

	String city;

	String street;

	String country;

	Integer postalCode;

	String number;

	Double rating;

}
