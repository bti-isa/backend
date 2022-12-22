package com.isa.BloodTransferInstitute.dto.user.patient;

import com.isa.BloodTransferInstitute.dto.address.NewAddressDTO;
import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class NewPatientDTO {

	@NotBlank
	String firstname;

	@NotBlank
	String lastname;

	@NotBlank
	@Email
	String email;

	@NotBlank
	@Size(min = 8, message = "Please, enter at least 8 characters for password.")
	String password;

	@NotBlank
	@Size(min = 9, message = "Please, enter at least 9 digits for phone number.")
	String phoneNumber;

	String occupation;

	String education;

	@NotBlank
	@Size(min = 13, message = "Please, enter at least 13 characters for jmbg.")
	String jmbg;

	@NotNull
	Gender gender;

	@NotNull
	Role role;

	@NotNull
	NewAddressDTO address;

	@NotNull
	BloodType bloodType;

}
