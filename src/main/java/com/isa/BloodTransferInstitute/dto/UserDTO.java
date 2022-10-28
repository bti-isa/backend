package com.isa.BloodTransferInstitute.dto;

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
public class UserDTO {

	Long id;

	String firstname;

	String lastname;

	String email;

	String jmbg;

	Gender gender;

	Role role;

	Integer penalties;

	List<AppointmentDTO> appointments;
}
