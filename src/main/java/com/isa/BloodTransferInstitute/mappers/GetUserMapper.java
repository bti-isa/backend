package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.LocationDTO;
import com.isa.BloodTransferInstitute.dto.address.AddressDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.PatientDTO;
import com.isa.BloodTransferInstitute.model.Address;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.Location;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserMapper {

	PatientDTO entityToDTO(User user);
	AddressDTO addressToDTO(Address address);
	LocationDTO locationToDTO(Location location);
	List<PatientDTO> entityListToDTOlist(List<User> users);
	AdminDTO entityToAdminDTO(User user);
	BloodBankDTO entityToBloodBankDTO(BloodBank bloodBank);
}
