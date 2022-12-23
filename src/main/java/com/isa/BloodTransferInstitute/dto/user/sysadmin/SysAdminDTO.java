package com.isa.BloodTransferInstitute.dto.user.sysadmin;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SysAdminDTO {
    Long id;
    String firstname;
    String lastname;
    String email;
    String jmbg;
    Gender gender;
    Role role;
    String phoneNumber;
}
