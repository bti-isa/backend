package com.isa.BloodTransferInstitute.repository.dto.user.admin;

import com.isa.BloodTransferInstitute.repository.dto.address.UpdateAddressDTO;
import com.isa.BloodTransferInstitute.enums.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAdminDTO {

    @NotNull
    Long id;

    @NotNull
    Boolean deleted;

    @NotBlank
    String firstname;

    @NotBlank
    String lastname;

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 9, message = "Please, enter at least 9 digits for phone number.")
    String phoneNumber;

    @NotBlank
    @Size(min = 13, message = "Please, enter at least 13 characters for jmbg.")
    String jmbg;

    @NotNull
    Gender gender;

    @NotNull
    Boolean accountActivated;

    @NotNull
	UpdateAddressDTO address;

    Long bloodBankId;
}
