package com.isa.BloodTransferInstitute.dto.user.admin;

import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisteredDonorsDTO {

    Long id;

    String firstname;

    String lastname;

    String username;

    String jmbg;

    Gender gender;

    String phoneNumber;

    BloodType bloodType;
}
