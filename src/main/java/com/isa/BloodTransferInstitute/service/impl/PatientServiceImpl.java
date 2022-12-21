package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.patient.CheckUniquePatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.SearchPatientDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.UpdatePatientDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.PatientMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.PatientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

	private final UserRepository userRepository;
	private final BloodBankRepository bloodBankRepository;
	private final EntityManager em;
	@Override
	@Transactional
	public User add(final NewPatientDTO dto) {
		return userRepository.save(PatientMapper.NewDTOToEntity(dto));
	}

	@Override
	@Transactional
	public User update(final UpdatePatientDTO dto) {
		//Treba pozvati servis i pokupiti listu appointment-a
		//final var appointments = originalAppointments.stream()
		//		.filter(originalAppointment -> dto.getAppointmentIds().contains(originalAppointment.getId())).toList();
		//updatedUser.setAppointments(dto.getAppointmentIds());
		final var user = userRepository.findById(dto.getId());
		if(user.isEmpty()) {
			return null;
		}
		return userRepository.save(PatientMapper.UpdateDTOToEntity(dto, user.get()));
	}

	@Override
	public Optional<User> get(final Long id) {
		return Optional.ofNullable(userRepository.findByIdAndRole(id, Role.PATIENT).orElseThrow(NotFoundException::new));
	}

	@Override
	public List<User> getAll() {
		return userRepository.findByRole(Role.PATIENT);
	}

	@Override
	public void delete(final Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public ArrayList<Boolean> checkUnique(CheckUniquePatientDTO dto){
		ArrayList<Boolean> retVal = new ArrayList<>();

		if(userRepository.findByUsername(dto.getEmail())==null)
			retVal.add(false);
		else
			retVal.add(true);

		if(userRepository.findByJmbg(dto.getJmbg())==null)
			retVal.add(false);
		else
			retVal.add(true);

		if(userRepository.findByPhoneNumber(dto.getPhoneNumber())==null)
			retVal.add(false);
		else
			retVal.add(true);

		return retVal;
	}

	@Override
	public List<User> search(SearchPatientDTO dto) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		final Root<User> user = criteriaQuery.from(User.class);
		final List<Predicate> predicates = new ArrayList<>();

		if (dto.getSearchParameter() != null && !dto.getSearchParameter().isEmpty()) {
			predicates.add(cb.like(user.get("firstname"), dto.getSearchParameter() + "%"));
		}
		if (dto.getSearchParameter() != null && !dto.getSearchParameter().isEmpty()) {
			predicates.add(cb.like(user.get("lastname"), dto.getSearchParameter() + "%"));
		}
		if (predicates.isEmpty()) {
			criteriaQuery.where(predicates.toArray(new Predicate[0]));
		} else {
			criteriaQuery.where(cb.and(predicates.toArray(new Predicate[0])));
		}

		final TypedQuery<User> querry = em.createQuery(criteriaQuery);

		return querry.getResultList();
	}
}
