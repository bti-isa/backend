package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.BloodBank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {
}
