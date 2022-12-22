package com.isa.BloodTransferInstitute.dto.user.sysadmin;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewSysAdminDTO {
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

    @NotBlank
    @Size(min = 13, message = "Please, enter at least 13 characters for jmbg.")
    String jmbg;

    @NotNull
    Gender gender;
}
