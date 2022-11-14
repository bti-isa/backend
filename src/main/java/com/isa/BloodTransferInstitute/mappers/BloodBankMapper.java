package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Location;
import org.springframework.stereotype.Component;

@Component
public class BloodBankMapper {
    public static BloodBank UpdateDTOtoEntity(final UpdateBloodBankDTO dto){
        final var updatedLocation = Location.builder()
                .id(dto.getUpdateAddressDTO().getLocationId())
                .latitude(dto.getUpdateAddressDTO().getLatitude())
                .longitude(dto.getUpdateAddressDTO().getLongitude())
                .build();

        final var updatedAddress = Address.builder()
                .id(dto.getUpdateAddressDTO().getId())
                .city(dto.getUpdateAddressDTO().getCity())
                .country(dto.getUpdateAddressDTO().getCountry())
                .street(dto.getUpdateAddressDTO().getStreet())
                .number(dto.getUpdateAddressDTO().getNumber())
                .postalCode(dto.getUpdateAddressDTO().getPostalCode())
                .location(updatedLocation)
                .build();

        final var updatedBank = BloodBank.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(updatedAddress)
                .rating(dto.getRating())
                .description(dto.getDescription())
                .build();

        return  updatedBank;
    }


}
