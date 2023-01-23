package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.complaint.AnswerDTO;
import com.isa.BloodTransferInstitute.dto.loyalty.LoyaltyDTO;
import com.isa.BloodTransferInstitute.enums.ComplaintStatus;
import com.isa.BloodTransferInstitute.model.Loyalty;
import com.isa.BloodTransferInstitute.service.LoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Loyalty")
@CrossOrigin(origins = "*")
public class LoyaltyController {
    private final LoyaltyService loyaltyService;

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<Loyalty> UpdateLoyalty(@Valid @NotNull @RequestBody final LoyaltyDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(loyaltyService.update(dto));
    }
}
