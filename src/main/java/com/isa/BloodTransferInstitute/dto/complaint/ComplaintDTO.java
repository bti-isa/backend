package com.isa.BloodTransferInstitute.dto.complaint;

import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComplaintDTO {
    Long id;
    String text;
    Long userId;
    String answer;
    String fullName;
    ComplaintStatus status;
}
