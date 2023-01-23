package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {
}
