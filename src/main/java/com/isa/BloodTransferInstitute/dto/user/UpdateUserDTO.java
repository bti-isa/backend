package com.isa.BloodTransferInstitute.dto.user;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import java.util.List;

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
public class UpdateUserDTO {

	Long id;

	String firstname;

	String lastname;

	String email;

	String password;

	String jmbg;

	Gender gender;

	Role role;

	Boolean active;

	Integer penalties;

	List<Long> appointmentIds;
}
