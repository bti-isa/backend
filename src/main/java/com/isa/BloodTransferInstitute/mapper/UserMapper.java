package com.isa.BloodTransferInstitute.mapper;

import com.isa.BloodTransferInstitute.dto.user.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.user.UpdateUserDTO;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User NewDTOToEntity (NewUserDTO dto);

	User UpdateDTOToEntity (UpdateUserDTO dto, List<Appointment> appointments);

}
