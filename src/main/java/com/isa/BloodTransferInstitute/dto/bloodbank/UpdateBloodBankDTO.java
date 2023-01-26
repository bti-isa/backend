package com.isa.BloodTransferInstitute.dto.bloodbank;

import com.isa.BloodTransferInstitute.dto.address.UpdateAddressDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UpdateBloodBankDTO {

    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotNull
	UpdateAddressDTO updateAddressDTO;

    @NotNull
    Double rating;

    @NotBlank
    String description;

}
