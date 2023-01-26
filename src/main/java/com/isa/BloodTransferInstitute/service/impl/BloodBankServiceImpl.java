package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.SearchDTO;
import com.isa.BloodTransferInstitute.dto.bloodbank.*;
import com.isa.BloodTransferInstitute.dto.user.admin.RegisteredDonorsDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.PatientDTO;
import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.BloodBankMapper;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.mappers.PatientMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.BloodUnit;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.BloodUnitRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.BloodBankService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BloodBankServiceImpl implements BloodBankService {

	private final BloodBankRepository bloodBankRepository;
	private final UserRepository userRepository;
	private final BloodUnitRepository bloodUnitRepository;
	private final EntityManager em;
	private static final String NAME = "name";
	private static final String ADDRESS = "address";
	private static final String CITY = "city";
	private static final String COUNTRY = "country";
	private static final String POSTAL_CODE = "postalCode";
	private static final String NUMBER = "number";
	private static final String STREET = "street";
	private static final String RATING = "rating";

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
			predicates.add(cb.like(bloodBank.get(NAME), searchDTO.getName() + "%"));
		}
		if (searchDTO.getCity() != null && !searchDTO.getCity().isEmpty()) {
			predicates.add(cb.like(bloodBank.get(ADDRESS).get(CITY), searchDTO.getCity() + "%"));
		}
		if (searchDTO.getCountry() != null && !searchDTO.getCountry().isEmpty()) {
			predicates.add(cb.like(bloodBank.get(ADDRESS).get(COUNTRY), searchDTO.getCountry() + "%"));
		}
		if (searchDTO.getNumber() != null && !searchDTO.getNumber().isEmpty()) {
			predicates.add(cb.like(bloodBank.get(ADDRESS).get(NUMBER), searchDTO.getNumber() + "%"));
		}
		if (searchDTO.getStreet() != null && !searchDTO.getStreet().isEmpty()) {
			predicates.add(cb.like(bloodBank.get(ADDRESS).get(STREET), searchDTO.getStreet() + "%"));
		}
		if (searchDTO.getPostalCode() != null) {
			predicates.add(cb.equal(bloodBank.get(ADDRESS).get(POSTAL_CODE), searchDTO.getPostalCode()));
		}
		if (searchDTO.getRating() != null) {
			predicates.add(cb.equal(bloodBank.get(RATING), searchDTO.getRating()));
		}

		if(predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
		} else {
			criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));
		}

		final TypedQuery<BloodBank> query = em.createQuery(criteriaQuery);
		query.setFirstResult((searchDTO.getPageNumber()) * searchDTO.getPageSize());
		query.setMaxResults(searchDTO.getPageSize());

		return query.getResultList();
	}

	@Override
	public List<BloodBank> getAll() {
		return bloodBankRepository.findAll();
	}

	@Override
	public Page<BloodBank> getAllWithPage(Pageable page){
		return bloodBankRepository.findAll(page);
	}

	@Override
	public void removeFromCache() {
		System.out.println("REMOVED");
	}

	public List<RegisteredDonorsDTO> getRegisteredDonors(Long id) {
		List<Tuple> queryResult = new ArrayList<>(userRepository.getRegisteredDonorsForBloodBank(id));
		if(queryResult.isEmpty())
			throw new NotFoundException();

		List<RegisteredDonorsDTO> returnList = new ArrayList<>();
		for(User user : userRepository.findAll()){
			for(var it : convertFromQueryResult(queryResult)){
				if(it.getPatientId().equals(user.getId())){
					var temp = PatientMapper.EntityToPatientDTO(user);
					temp.setDonateDate(it.getAppointmentDate());
					returnList.add(temp);
				}
			}
		}
		return  returnList;
	}

	private List<DonorsQueryDTO> convertFromQueryResult(List<Tuple> queryResult){
		List<DonorsQueryDTO> converted = queryResult.stream()
				.map(t -> new DonorsQueryDTO(
						t.get(0, Long.class),
						t.get(1, LocalDateTime.class)
				))
				.collect(Collectors.toList());

		return converted;
	}

	@Override
	public Long getByAdminId(String email) {
		return userRepository.getBloodBankByUserId(email);
	}

	@Override
	public List<BloodUnitDTO> getBloodUnits(Long id) {
		var result = new ArrayList<>(bloodUnitRepository.getBloodUnits(id));
		return convertFromUnitQueryResult(result);
	}

	private List<BloodUnitDTO> convertFromUnitQueryResult(List<Tuple> queryResult){
		List<BloodUnitWTFDTO> converted = queryResult.stream()
				.map(t -> new BloodUnitWTFDTO(
						t.get(0, BigInteger.class).longValue(),
						t.get(1, Integer.class),
						t.get(2, BigDecimal.class).intValue()
				))
				.collect(Collectors.toList());

		return getBloodTypes(converted);
	}

	private List<BloodUnitDTO> getBloodTypes(List<BloodUnitWTFDTO> list){
		List<BloodUnitDTO> retList = new ArrayList<>();
		for(var it: list){
			retList.add(new BloodUnitDTO(it.getId(),BloodType.values()[it.getBloodType()],it.getQuantity()));
		}
		return  retList;
	}
}
