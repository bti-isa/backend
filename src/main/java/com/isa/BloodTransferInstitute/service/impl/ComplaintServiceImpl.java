package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.complaint.AnswerDTO;
import com.isa.BloodTransferInstitute.dto.complaint.ComplaintDTO;
import com.isa.BloodTransferInstitute.dto.complaint.NewComplaintDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.mappers.ComplaintMapper;
import com.isa.BloodTransferInstitute.model.Complaint;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.ComplaintRepository;
import com.isa.BloodTransferInstitute.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;

    @Override
    public Optional<Complaint> getById(final Long id) {
        return Optional.ofNullable(complaintRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    public Complaint add(NewComplaintDTO dto){
        return complaintRepository.save(complaintMapper.DTOToEntity(dto));
    }
    @Override
    public Complaint updateComplicated(AnswerDTO dto) {
        Integer counter = 0;
        Complaint complaint = getById(dto.getId()).get();   //dobavljam
        complaint.setAnswer(dto.getAnswer());
        complaint.setStatus(ComplaintStatus.ANSWERED);  //izmenim
        while(counter < 5) {
            try {
                complaintRepository.save(complaint);    //save proveri verziju u bazi ako je veca od lokalne baci
                break;
            } catch (OptimisticLockingFailureException ex) {    //ovo
                complaint = getById(dto.getId()).get(); //u tom slucaju opet dobavim
                complaint.setAnswer(dto.getAnswer());   //opet izmenim
                complaint.setStatus(ComplaintStatus.ANSWERED);
                counter++;  //ovo mi escape condicion
            }
        }
        if(counter == 5) {
            throw new RuntimeException("Update not successful!");
        }
        return complaint;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE,
    timeout = 5, readOnly = false)
    public Complaint update(AnswerDTO dto) {
        Complaint complaint = getById(dto.getId()).get();
        complaint.setAnswer(dto.getAnswer());
        complaint.setStatus(ComplaintStatus.ANSWERED);
        return complaintRepository.save(complaint);
    }
    @Override
    public List<ComplaintDTO> getAllByStatus(ComplaintStatus status) {
        List<Complaint> tempList = complaintRepository.findAll();
        List<ComplaintDTO> retList = new ArrayList<ComplaintDTO>();
        for(Complaint complaint : tempList) {
            if(complaint.getStatus() == status) {
                retList.add(complaintMapper.EntityToDTO(complaint));
            }
        }
        return retList;
    }
    @Override
    public List<ComplaintDTO> getAllAnswered() {
        List<Complaint> tempList = complaintRepository.findAll();
        List<ComplaintDTO> retList = new ArrayList<ComplaintDTO>();
        for(Complaint complaint : tempList) {
            if(complaint.getStatus() == ComplaintStatus.ANSWERED) {
                retList.add(complaintMapper.EntityToDTO(complaint));
            }
        }
        return retList;
    }
}
