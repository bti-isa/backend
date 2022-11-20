package com.isa.BloodTransferInstitute.dto.appointment.mappers;

import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetBloodBankMapper {

	BloodBankDTO EntityToEntityDTO(BloodBank bloodBank);

	List<BloodBankDTO> ListToListDTO(List<BloodBank> bloodBankList);
}
