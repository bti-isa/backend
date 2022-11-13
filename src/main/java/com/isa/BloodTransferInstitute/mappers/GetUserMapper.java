package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.UserDTO;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper {

	UserDTO entityToDTO(User user);

	List<UserDTO> entityListToDTOlist(List<User> users);
}
