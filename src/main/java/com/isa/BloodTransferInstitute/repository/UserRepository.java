package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(Role role);
	User findByUsername(String username);
	User findByJmbg(String jmbg);
	User findByPhoneNumber(String phoneNumber);
	Optional<User> findByIdAndRole(Long id, Role role);
}
