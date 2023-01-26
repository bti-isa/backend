package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodUnitDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.util.List;

import com.isa.BloodTransferInstitute.model.BloodUnit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetBloodBankMapper {

	BloodBankDTO EntityToEntityDTO(BloodBank bloodBank);

	List<BloodBankDTO> ListToListDTO(List<BloodBank> bloodBankList);

	BloodUnitDTO BloodUnitToDTO(BloodUnit bloodUnit);
}
