package com.isa.BloodTransferInstitute.repository.dto.user.admin;

import com.isa.BloodTransferInstitute.repository.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminDTO {

    Long id;
    String firstname;
    String lastname;
    String email;
    String jmbg;
    Gender gender;
    Role role;
    String phoneNumber;
    AddressDTO address;
    BloodBankDTO bloodBank;

}
