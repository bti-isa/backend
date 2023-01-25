package com.isa.BloodTransferInstitute.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CordDTO {
    List<Double> startLocation;
    List<Double> endLocation;
    int frequency;
}
