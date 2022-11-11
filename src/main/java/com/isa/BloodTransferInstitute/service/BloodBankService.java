package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;

import java.util.List;

public interface BloodBankService {

	List<BloodBank> search(SearchDTO searchDTO);

}
