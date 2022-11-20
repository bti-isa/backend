package com.isa.BloodTransferInstitute.repository.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordDTO {

    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

    @NotBlank
    String confirmPassword;

}
