package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.patient.NewPatientDTO;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*")
public class AdminController {
    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final GetUserMapper getUserMapper;
    @PostMapping("/add")
    public ResponseEntity<AdminDTO> addNewAdmin(@Valid @NotNull @RequestBody final NewAdminDTO dto) {
        final var admin = adminService.add(dto);
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.AdminToAdminDTO(admin));
    }

    @PutMapping("/update")
    public ResponseEntity<AdminDTO> update(@Valid @NonNull @RequestBody final UpdateAdminDTO dto){
        final var updatedUser = adminService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.AdminToAdminDTO(updatedUser));
    }

}
