package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.model.Poll;
import com.isa.BloodTransferInstitute.repository.PollRepository;
import com.isa.BloodTransferInstitute.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    PollRepository pollRepository;

    public void add(Poll poll){
        pollRepository.save(poll);
    }
}
