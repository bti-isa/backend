package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.UpdateUserDTO;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	User add(NewUserDTO dto);

	User update(UpdateUserDTO dto);

	Optional<User> get(Long id);

	List<User> getAll();

	void delete(Long id);

}
