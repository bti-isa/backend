package com.isa.BloodTransferInstitute.dto.user;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class NewUserDTO {

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
	@Size(min = 13, message = "Please, enter at least 13 characters for jmbg.")
	String jmbg;

	@NotNull
	Gender gender;

	@NotNull
	Role role;

}
