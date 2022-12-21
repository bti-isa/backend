package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.complaint.AnswerDTO;
import com.isa.BloodTransferInstitute.dto.complaint.ComplaintDTO;
import com.isa.BloodTransferInstitute.dto.complaint.NewComplaintDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import com.isa.BloodTransferInstitute.mappers.ComplaintMapper;
import com.isa.BloodTransferInstitute.model.Complaint;
import com.isa.BloodTransferInstitute.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Complaint")
@CrossOrigin(origins = "*")
public class ComplaintController {
    private final ComplaintService complaintService;
    private final ComplaintMapper complaintMapper;

    @PostMapping("/add")
    public ResponseEntity<ComplaintDTO> addNewComplaint(@Valid @NotNull @RequestBody final NewComplaintDTO dto) {
        final var complaint = complaintService.add(dto);
        return ResponseEntity.status(HttpStatus.OK).body(complaintMapper.EntityToDTO(complaint));
    }
    @PatchMapping("/answer")
    public ResponseEntity<ComplaintDTO> respondToComplaint(@Valid @NotNull @RequestBody final AnswerDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(complaintMapper.EntityToDTO(complaintService.update(dto)));
    }
    @GetMapping("/pending")
    public ResponseEntity<List<ComplaintDTO>> getAllPending(){
        return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllByStatus(ComplaintStatus.PENDING));
    }
    @GetMapping("/answered")
    public ResponseEntity<List<ComplaintDTO>> getAllAnswered(){
        return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllByStatus(ComplaintStatus.ANSWERED));
    }
}
