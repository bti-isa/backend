package com.isa.BloodTransferInstitute.dto.user.admin;

import com.isa.BloodTransferInstitute.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.model.BloodBank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminDTO {

    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String jmbg;
    Gender gender;
    Boolean accountActivated;
    AddressDTO address;
    BloodBankDTO bloodBank;
}
