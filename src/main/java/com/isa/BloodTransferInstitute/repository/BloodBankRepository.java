package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.BloodBank;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

}
