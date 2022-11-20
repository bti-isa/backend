package com.isa.BloodTransferInstitute.repository.dto.bloodbank;

import com.isa.BloodTransferInstitute.model.BloodBank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
public class SimpleBloodBankDTO {
    Long id;
    String name;
}
