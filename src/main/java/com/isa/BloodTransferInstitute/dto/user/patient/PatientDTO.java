package com.isa.BloodTransferInstitute.dto.user.patient;

import com.isa.BloodTransferInstitute.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

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
public class PatientDTO {

	Long id;

	Boolean enabled;

	Boolean deleted;

	String firstname;

	String lastname;

	String username;

	String jmbg;

	Gender gender;

	Role role;

	String phoneNumber;

	String occupation;

	String education;

	Integer penalties;

	AddressDTO address;

	BloodType bloodType;
}
