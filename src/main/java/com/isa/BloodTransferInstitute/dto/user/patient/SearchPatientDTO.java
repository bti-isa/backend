package com.isa.BloodTransferInstitute.dto.user.patient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchPatientDTO {
    String searchParameter;
}
