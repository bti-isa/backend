package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.repository.dto.SearchDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.NewBloodBankDTO;
import com.isa.BloodTransferInstitute.repository.dto.bloodbank.UpdateBloodBankDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.repository.dto.appointment.mappers.BloodBankMapper;
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

	public BloodBank add(NewBloodBankDTO dto) {
		return bloodBankRepository.save(BloodBankMapper.NewDTOToEntity(dto));
	}

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

		if (searchDTO.getName() != null && !searchDTO.getName().isEmpty()) {
			predicates.add(cb.like(bloodBank.get("name"), searchDTO.getName() + "%"));
		}
		if (searchDTO.getCity() != null && !searchDTO.getCity().isEmpty()) {
			predicates.add(cb.like(bloodBank.get("address").get("city"), searchDTO.getCity() + "%"));
		}
		if (searchDTO.getCountry() != null && !searchDTO.getCountry().isEmpty()) {
			predicates.add(cb.like(bloodBank.get("address").get("country"), searchDTO.getCountry() + "%"));
		}
		if (searchDTO.getNumber() != null && !searchDTO.getNumber().isEmpty()) {
			predicates.add(cb.like(bloodBank.get("address").get("number"), searchDTO.getNumber() + "%"));
		}
		if (searchDTO.getStreet() != null && !searchDTO.getStreet().isEmpty()) {
			predicates.add(cb.like(bloodBank.get("address").get("street"), searchDTO.getStreet() + "%"));
		}
		if (searchDTO.getPostalCode() != null) {
			predicates.add(cb.equal(bloodBank.get("address").get("postalCode"), searchDTO.getPostalCode()));
		}
		if (searchDTO.getRating() != null) {
			predicates.add(cb.equal(bloodBank.get("rating"), searchDTO.getRating()));
		}

		if(predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
		} else {
			criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));
		}

		final TypedQuery<BloodBank> query = em.createQuery(criteriaQuery);
//		query.setFirstResult((searchDTO.getPageNumber()) * searchDTO.getPageSize());
//		query.setMaxResults(searchDTO.getPageSize());

		return query.getResultList();
	}

	@Override
	public List<BloodBank> getAll() {
		return bloodBankRepository.findAll();
	}

}
