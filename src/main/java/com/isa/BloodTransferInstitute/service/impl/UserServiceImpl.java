package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.user.UpdateUserDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mapper.UserMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public User add(final NewUserDTO dto) {
			return userRepository.save(userMapper.NewDTOToEntity(dto));
	}

	@Override
	public User update(final UpdateUserDTO dto) {
		//Treba pozvati servis i pokupiti listu appointment-a
		return userRepository.save(userMapper.UpdateDTOToEntity(dto, null));
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
