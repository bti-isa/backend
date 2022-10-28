package com.isa.BloodTransferInstitute.mapper;

import com.isa.BloodTransferInstitute.dto.UserDTO;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface GetUserMapper {

	UserDTO entityToDTO(User user);

	List<UserDTO> entityListToDTOlist(List<User> users);
}
