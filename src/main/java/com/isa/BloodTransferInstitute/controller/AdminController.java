package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Admin")
public class AdminController {

    private final AdminService adminService;

    private final GetUserMapper getUserMapper;

    @PutMapping("/update")
    public ResponseEntity<AdminDTO> update(@Valid @NonNull @RequestBody final UpdateAdminDTO dto){
        final var updatedUser = adminService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.EntityToEntityDTO(updatedUser));
    }

}
