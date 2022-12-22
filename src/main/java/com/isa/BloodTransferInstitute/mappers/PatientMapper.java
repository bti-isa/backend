package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.UpdatePatientDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;

import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

	public static User NewDTOToEntity(final NewPatientDTO dto) {

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
			.username(dto.getUsername())
			.password(dto.getPassword())
			.phoneNumber(dto.getPhoneNumber())
			.occupation(dto.getOccupation())
			.education(dto.getEducation())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole())
			.deleted(false)
			.penalties(0)
			.enabled(true)
			.address(address)
			.bloodType(dto.getBloodType())
			.build();

		return newUser;
	}

	public static User UpdateDTOToEntity(final UpdatePatientDTO dto, final User user) {

		final var updatedLocation = user.getAddress().getLocation().toBuilder()
			.id(dto.getAddress().getLocationId())
			.longitude(dto.getAddress().getLongitude())
			.latitude(dto.getAddress().getLatitude())
			.build();

		final var updatedAddress = user.getAddress().toBuilder()
			.id(dto.getAddress().getId())
			.city(dto.getAddress().getCity())
			.country(dto.getAddress().getCountry())
			.street(dto.getAddress().getStreet())
			.number(dto.getAddress().getNumber())
			.postalCode(dto.getAddress().getPostalCode())
			.location(updatedLocation)
			.build();

		return user.toBuilder()
			.id(dto.getId())
			.deleted(dto.getDeleted())
			.firstname(dto.getFirstname())
			.lastname(dto.getLastname())
			.phoneNumber(dto.getPhoneNumber())
			.occupation(dto.getOccupation())
			.education(dto.getEducation())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole())
			.enabled(dto.getAccountActivated())
			.address(updatedAddress)
			.bloodType(dto.getBloodType())
			.build();
	}

}
