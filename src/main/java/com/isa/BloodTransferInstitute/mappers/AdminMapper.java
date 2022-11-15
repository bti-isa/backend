package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public static User UpdateDTOtoEntity(final UpdateAdminDTO dto, String password, BloodBank bloodBank){
        final var updatedLocation = Location.builder()
                .id(dto.getAddress().getId())
                .longitude(dto.getAddress().getLongitude())
                .latitude(dto.getAddress().getLatitude())
                .build();

        final var updatedAddress = Address.builder()
                .id(dto.getAddress().getId())
                .city(dto.getAddress().getCity())
                .street(dto.getAddress().getStreet())
                .postalCode(dto.getAddress().getPostalCode())
                .number(dto.getAddress().getNumber())
                .country(dto.getAddress().getCountry())
                .location(updatedLocation)
                .build();

        final var updatedUser = User.builder()
                .id(dto.getId())
                .deleted(dto.getDeleted())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .email(dto.getEmail())
                .password(password)
                .phoneNumber(dto.getPhoneNumber())
                .jmbg(dto.getJmbg())
                .gender(dto.getGender())
                .role(Role.INSTITUTE_ADMIN)
                .accountActivated(dto.getAccountActivated())
                .address(updatedAddress)
                .bloodBank(bloodBank)
                .build();

        return updatedUser;
    }
}
