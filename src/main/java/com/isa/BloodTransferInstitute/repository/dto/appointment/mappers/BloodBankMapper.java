package com.isa.BloodTransferInstitute.repository.dto.appointment.mappers;

import com.isa.BloodTransferInstitute.repository.dto.bloodbank.NewBloodBankDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.SimpleBloodBankDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Location;
import org.springframework.stereotype.Component;

@Component
public class BloodBankMapper {

    public static BloodBank NewDTOToEntity(final NewBloodBankDTO dto) {
        final var location = Location.builder()
                .latitude(dto.getAddress().getLatitude())
                .longitude(dto.getAddress().getLongitude())
                .build();
        final var address = Address.builder()
                .city(dto.getAddress().getCity())
                .street(dto.getAddress().getStreet())
                .country(dto.getAddress().getCountry())
                .postalCode(dto.getAddress().getPostalCode())
                .number(dto.getAddress().getNumber())
                .location(location)
                .build();
        final var bloodBank = BloodBank.builder()
                .name(dto.getName())
                .address(address)
                .description(dto.getDescription())
                .build();
        return bloodBank;
    }
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
    public static SimpleBloodBankDTO EntityToSimpleDTO(BloodBank bloodBank) {
        var simple = SimpleBloodBankDTO.builder()
                .id(bloodBank.getId())
                .name(bloodBank.getName())
                .build();
        return simple;
    }


}
