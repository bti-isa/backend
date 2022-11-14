package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public static User UpdateDTOToEntity(final UpdateAdminDTO dto, BloodBank bloodBank) {
        final var updatedLocation = Location.builder()
                .id(dto.getAddress().getLocationId())
                .longitude(dto.getAddress().getLongitude())
                .latitude(dto.getAddress().getLatitude())
                .build();

        final var updatedAddress = Address.builder()
                .id(dto.getAddress().getId())
                .city(dto.getAddress().getCity())
                .country(dto.getAddress().getCountry())
                .street(dto.getAddress().getStreet())
                .number(dto.getAddress().getNumber())
                .postalCode(dto.getAddress().getPostalCode())
                .location(updatedLocation)
                .build();

        final var updatedAdmin = User.builder()
                .id(dto.getId())
                .deleted(dto.getDeleted())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .jmbg(dto.getJmbg())
                .gender(dto.getGender())
                .address(updatedAddress)
                .accountActivated(dto.getAccountActivated())
                .bloodBank(bloodBank)
                .build();

        return updatedAdmin;
    }
}
