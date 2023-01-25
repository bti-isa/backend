package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.enums.BloodType;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.BloodUnit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodUnitRepository extends JpaRepository<BloodUnit, Long> {
	BloodUnit findByBloodType(BloodType bloodType);
	BloodUnit findByBloodTypeAndBloodBankId(BloodType bloodType, Long bloodBankId);
}
