package com.isa.BloodTransferInstitute.repository.dto.bloodbank;

import com.isa.BloodTransferInstitute.repository.dto.address.UpdateAddressDTO;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
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
