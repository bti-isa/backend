package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.BloodUnitDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.NewBloodBankDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.RegisteredDonorsDTO;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.BloodUnit;
import com.isa.BloodTransferInstitute.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;


public interface BloodBankService {

	List<BloodBank> search(SearchDTO searchDTO);
	BloodBank add(NewBloodBankDTO dto);
	@Cacheable(value = "bank")
	Optional<BloodBank> getById(Long id);
	BloodBank update(UpdateBloodBankDTO dto);
	List<BloodBank> getAll();
	Page<BloodBank> getAllWithPage(Pageable page);
	List<RegisteredDonorsDTO> getRegisteredDonors(Long id);
	Long getByAdminId(String email);
	List<BloodUnitDTO> getBloodUnits(Long id);
	@CacheEvict(cacheNames = {"product"}, allEntries = true)
	void removeFromCache();
}
