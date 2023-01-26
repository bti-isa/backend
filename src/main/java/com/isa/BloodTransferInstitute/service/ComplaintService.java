package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.complaint.AnswerDTO;
import com.isa.BloodTransferInstitute.dto.complaint.ComplaintDTO;
import com.isa.BloodTransferInstitute.dto.complaint.NewComplaintDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import com.isa.BloodTransferInstitute.model.Complaint;
import com.isa.BloodTransferInstitute.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    Optional<Complaint> getById(Long id);
    Complaint add(NewComplaintDTO dto);
    Complaint updateComplicated(AnswerDTO dto);
    Complaint update(AnswerDTO dto);
    List<ComplaintDTO> getAllAnswered();
    List<ComplaintDTO> getAllByStatus(ComplaintStatus status);
}
