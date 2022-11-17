package com.isa.BloodTransferInstitute.repository;

import com.isa.BloodTransferInstitute.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PollRepository extends JpaRepository<Poll,Long> {
}
