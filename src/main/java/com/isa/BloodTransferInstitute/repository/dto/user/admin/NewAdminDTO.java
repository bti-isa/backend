package com.isa.BloodTransferInstitute.repository.dto.user.admin;

import com.isa.BloodTransferInstitute.repository.dto.address.NewAddressDTO;
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
public class NewAdminDTO {
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
    Long bloodBankId;
}
