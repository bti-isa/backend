package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.LocationDTO;
import com.isa.BloodTransferInstitute.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.dto.user.UserDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper {

	UserDTO entityToDTO(User user);
	AddressDTO addressToDTO(Address address);
	LocationDTO locationToDTO(Location location);
	List<UserDTO> entityListToDTOlist(List<User> users);
}
