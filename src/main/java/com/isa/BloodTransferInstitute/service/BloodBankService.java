package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.util.List;
import java.util.Optional;

public interface BloodBankService {

	List<BloodBank> search(SearchDTO searchDTO);
	Optional<BloodBank> getById(Long id);
	BloodBank update(UpdateBloodBankDTO dto);
}
