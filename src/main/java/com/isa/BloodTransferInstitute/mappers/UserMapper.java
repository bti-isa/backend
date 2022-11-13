package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.user.UpdateUserDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public static User NewDTOToEntity(final NewUserDTO dto) {

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

		final var newUser = User.builder()
			.firstname(dto.getFirstname())
			.lastname(dto.getLastname())
			.email(dto.getEmail())
			.password(dto.getPassword())
			.phoneNumber(dto.getPhoneNumber())
			.occupation(dto.getOccupation())
			.education(dto.getEducation())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole())
			.deleted(false)
			.penalties(0)
			.accountActivated(true)
			.address(address)
			.build();

		return newUser;
	}

	public static User UpdateDTOToEntity(final UpdateUserDTO dto) {


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

		final var updateUser = User.builder()
			.id(dto.getId())
			.deleted(dto.getDeleted())
			.firstname(dto.getFirstname())
			.lastname(dto.getLastname())
			.email(dto.getEmail())
			.password(dto.getPassword())
			.phoneNumber(dto.getPhoneNumber())
			.occupation(dto.getOccupation())
			.education(dto.getEducation())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole())
			.accountActivated(dto.getAccountActivated())
			.penalties(dto.getPenalties())
			.address(updatedAddress)
			.build();

		return updateUser;
	}

}
