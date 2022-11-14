package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.UpdatePatientDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.PatientMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.AddressRepository;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.LocationRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.PatientService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

	private final UserRepository userRepository;
	private final BloodBankRepository bloodBankRepository;

	@Override
	@Transactional
	public User add(final NewPatientDTO dto) {
		return userRepository.save(PatientMapper.NewDTOToEntity(dto));
	}

	@Override
	@Transactional
	public User update(final UpdatePatientDTO dto) {
		//Treba pozvati servis i pokupiti listu appointment-a
		//final var appointments = originalAppointments.stream()
		//		.filter(originalAppointment -> dto.getAppointmentIds().contains(originalAppointment.getId())).toList();
		//updatedUser.setAppointments(dto.getAppointmentIds());
		
		return userRepository.save(PatientMapper.UpdateDTOToEntity(dto));
	}

	@Override
	public Optional<User> get(final Long id) {
		return Optional.ofNullable(userRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(final Long id) {
		userRepository.deleteById(id);
	}
}
