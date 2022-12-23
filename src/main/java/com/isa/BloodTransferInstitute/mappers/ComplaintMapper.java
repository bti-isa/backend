package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.complaint.ComplaintDTO;
import com.isa.BloodTransferInstitute.dto.complaint.NewComplaintDTO;
import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import com.isa.BloodTransferInstitute.model.Complaint;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.ComplaintService;
import com.isa.BloodTransferInstitute.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ComplaintMapper {
    private final PatientService patientService;

    public Complaint DTOToEntity(NewComplaintDTO dto){
        final var complaint = Complaint.builder()
                .text(dto.getText())
                .status(ComplaintStatus.PENDING)
                .user(patientService.get(dto.getUserId()).get())
                .build();

        return complaint;

    }
    public ComplaintDTO EntityToDTO(Complaint complaint) {
        var temp = complaint.getAnswer();
        if(temp == null) temp = "";
        final var dto = ComplaintDTO.builder()
                .id(complaint.getId())
                .answer(temp)
                .fullName(complaint.getUser().getFirstname()
                        .concat(" ")
                        .concat(complaint.getUser().getLastname()))
                .userId(complaint.getUser().getId())
                .text(complaint.getText())
                .status(complaint.getStatus())
                .build();

        return dto;
    }
}
