package com.isa.BloodTransferInstitute.dto.transfer;

import com.isa.BloodTransferInstitute.enums.BloodType;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractDTO {

	BloodType bloodType;

	int quantity;

}
