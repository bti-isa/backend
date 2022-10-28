package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
