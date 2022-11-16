package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.NewBloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.util.List;
import java.util.Optional;


public interface BloodBankService {

	List<BloodBank> search(SearchDTO searchDTO);
	BloodBank add(NewBloodBankDTO dto);
	Optional<BloodBank> getById(Long id);
	BloodBank update(UpdateBloodBankDTO dto);
	List<BloodBank> getAll();

}
