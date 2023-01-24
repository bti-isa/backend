package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.model.BloodUnit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodUnitRepository extends JpaRepository<BloodUnit, Long> {
	BloodUnit findByBloodType(BloodType bloodType);
	BloodUnit findByBloodTypeAndBloodBankId(BloodType bloodType, Long bloodBankId);
}
