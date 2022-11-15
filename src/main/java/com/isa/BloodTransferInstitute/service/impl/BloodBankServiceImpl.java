package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.BloodBankMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.service.BloodBankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BloodBankServiceImpl implements BloodBankService {

	private final BloodBankRepository bloodBankRepository;
	private final EntityManager em;

	@Override
	public Optional<BloodBank> getById(final Long id) {
		return Optional.ofNullable(bloodBankRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@Override
	public BloodBank update(final UpdateBloodBankDTO dto) {
		return bloodBankRepository.save(BloodBankMapper.UpdateDTOtoEntity(dto));
	}

	@Override
	public List<BloodBank> search(final SearchDTO searchDTO) {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<BloodBank> criteriaQuery = cb.createQuery(BloodBank.class);
		final Root<BloodBank> bloodBank = criteriaQuery.from(BloodBank.class);
		final List<Predicate> predicates = new ArrayList<>();

		if (searchDTO.getName() != null) {
			predicates.add(cb.equal(bloodBank.get("name"), searchDTO.getName()));
		}
		if (searchDTO.getCity() != null) {
			predicates.add(cb.equal(bloodBank.get("city"), searchDTO.getCity()));
		}
		if (searchDTO.getCountry() != null) {
			predicates.add(cb.equal(bloodBank.get("country"), searchDTO.getCountry()));
		}
		if (searchDTO.getNumber() != null) {
			predicates.add(cb.equal(bloodBank.get("number"), searchDTO.getNumber()));
		}
		if (searchDTO.getStreet() != null) {
			predicates.add(cb.equal(bloodBank.get("street"), searchDTO.getStreet()));
		}
		if (searchDTO.getPostalCode() != null) {
			predicates.add(cb.equal(bloodBank.get("postalCode"), searchDTO.getPostalCode()));
		}
		if (searchDTO.getRating() != null) {
			predicates.add(cb.equal(bloodBank.get("rating"), searchDTO.getRating()));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		final TypedQuery<BloodBank> query = em.createQuery(criteriaQuery);
		query.setFirstResult((searchDTO.getPageNumber()) * searchDTO.getPageSize());
		query.setMaxResults(searchDTO.getPageSize());

		return query.getResultList();
	}

	@Override
	public List<BloodBank> getAll() {
		return bloodBankRepository.findAll();
	}

}
