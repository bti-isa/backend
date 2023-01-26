package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.dto.bloodbank.DonorsQueryDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(Role role);
	User findByUsername(String username);
	User findByJmbg(String jmbg);
	User findByPhoneNumber(String phoneNumber);
	Optional<User> findByIdAndRole(Long id, Role role);

	@Query("SELECT u.id, a.dateTime FROM appointments a LEFT JOIN users u ON a.patient.id = u.id WHERE a.bloodBank.id = ?1 AND a.finished = 1 AND a.status = 2 AND u.enabled = 1")
	Collection<Tuple> getRegisteredDonorsForBloodBank(Long bloodBankId);

	@Query("SELECT u.bloodBank.id FROM users u WHERE u.id = ?1")
	Long getBloodBankByUserId(Long id);
}
