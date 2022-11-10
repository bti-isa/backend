package com.isa.BloodTransferInstitute.mapper.impl;

import com.isa.BloodTransferInstitute.dto.user.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.user.UpdateUserDTO;
import com.isa.BloodTransferInstitute.mapper.UserMapper;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

public class UserMapperImpl implements UserMapper {

	@Override
	public User NewDTOToEntity(final NewUserDTO dto) {

		final var newUser = User.builder()
			.firstname(dto.getFirstname())
			.lastname(dto.getLastname())
			.email(dto.getEmail())
			.password(dto.getPassword())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole()).build();

		return newUser;
	}

	@Override
	public User UpdateDTOToEntity(final UpdateUserDTO dto, final List<Appointment> originalAppointments) {

		final var appointments = originalAppointments.stream()
			.filter(originalAppointment -> dto.getAppointmentIds().contains(originalAppointment.getId())).toList();

		final var updateUser = User.builder()
			.id(dto.getId())
			.firstname(dto.getFirstname())
			.lastname(dto.getLastname())
			.email(dto.getEmail())
			.password(dto.getPassword())
			.jmbg(dto.getJmbg())
			.gender(dto.getGender())
			.role(dto.getRole())
			.active(dto.getActive())
			.penalties(dto.getPenalties())
			.appointments(appointments)
			.build();

		return updateUser;
	}
}
