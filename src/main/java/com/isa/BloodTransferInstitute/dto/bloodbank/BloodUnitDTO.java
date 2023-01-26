package com.isa.BloodTransferInstitute.dto.bloodbank;

import com.isa.BloodTransferInstitute.enums.BloodType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BloodUnitDTO {

    Long id;

    BloodType bloodType;

    int quantity;
}
