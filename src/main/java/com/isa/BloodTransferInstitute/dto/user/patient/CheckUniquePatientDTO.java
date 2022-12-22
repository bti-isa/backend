package com.isa.BloodTransferInstitute.dto.user.patient;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckUniquePatientDTO {
    String username;

    String jmbg;

    String phoneNumber;
}
