package com.isa.BloodTransferInstitute.repository.dto.bloodbank;

import com.isa.BloodTransferInstitute.repository.dto.address.NewAddressDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewBloodBankDTO {

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
	NewAddressDTO address;
}
