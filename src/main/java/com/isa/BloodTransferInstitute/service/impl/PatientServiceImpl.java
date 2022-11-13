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
	private final LocationRepository locationRepository;
	private final AddressRepository addressRepository;
	private final BloodBankRepository bloodBankRepository;

	@Override
	@Transactional
	public User add(final NewPatientDTO dto) {
			User newUser = PatientMapper.NewDTOToEntity(dto);
			locationRepository.save(newUser.getAddress().getLocation());
			addressRepository.save(newUser.getAddress());
			return userRepository.save(newUser);
	}

	@Override
	@Transactional
	public User update(final UpdatePatientDTO dto) {
		//Treba pozvati servis i pokupiti listu appointment-a
		//Bloodbank kad se upise u bazu onda otkomentarisati
//		BloodBank bloodBank = new BloodBank();
//		if(bloodBankRepository.findById(dto.getBloodBankId()).isPresent()) {
//			bloodBank = bloodBankRepository.findById(dto.getBloodBankId()).get();
//		}

		User updatedUser = PatientMapper.UpdateDTOToEntity(dto);
		//updatedUser.setBloodBank(bloodBank);
		//final var appointments = originalAppointments.stream()
		//		.filter(originalAppointment -> dto.getAppointmentIds().contains(originalAppointment.getId())).toList();
		//updatedUser.setAppointments(dto.getAppointmentIds());

		locationRepository.save(updatedUser.getAddress().getLocation());
		addressRepository.save(updatedUser.getAddress());
		return userRepository.save(updatedUser);
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
