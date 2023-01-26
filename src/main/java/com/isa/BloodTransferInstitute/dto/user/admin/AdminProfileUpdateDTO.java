package com.isa.BloodTransferInstitute.dto.user.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminProfileUpdateDTO {

    @NotNull
    Long id;

    @NotBlank
    String enteredFirstName;

    @NotBlank
    String enteredLastName;

    @NotBlank
    String enteredCity;

    @NotBlank
    String enteredNumber;

    @NotBlank
    String enteredPhone;

    @NotNull
    Integer enteredPostalCode;

    @NotBlank
    String enteredStreet;
}
