package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Admin")
public class AdminController {

    private final AdminService adminService;
    private final GetUserMapper mapper;

    @PutMapping("/update")
    public ResponseEntity<AdminDTO> update(@Valid @NotNull @RequestBody final UpdateAdminDTO dto){
        var updatedAdmin = adminService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.entityToAdminDTO(updatedAdmin));
    }
}
