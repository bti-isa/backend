package com.isa.BloodTransferInstitute.dto.complaint;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewComplaintDTO {
    String text;
    Long userId;
}
