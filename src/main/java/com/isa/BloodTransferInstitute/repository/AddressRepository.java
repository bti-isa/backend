package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
