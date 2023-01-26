package com.isa.BloodTransferInstitute.dto.bloodbank;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DonorsQueryDTO {

    Long patientId;

    LocalDateTime appointmentDate;
}
