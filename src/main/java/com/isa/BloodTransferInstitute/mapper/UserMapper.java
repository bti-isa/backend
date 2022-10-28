package com.isa.BloodTransferInstitute.mapper;

import com.isa.BloodTransferInstitute.dto.NewUserDTO;
import com.isa.BloodTransferInstitute.dto.UpdateUserDTO;
import com.isa.BloodTransferInstitute.model.Appointment;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

	User NewDTOToEntity (NewUserDTO dto);

	User UpdateDTOToEntity (UpdateUserDTO dto, List<Appointment> appointments);

}
