package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.sysadmin.NewSysAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.sysadmin.SysAdminDTO;
import com.isa.BloodTransferInstitute.mappers.SysAdminMapper;
import com.isa.BloodTransferInstitute.service.SysAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/SysAdmin")
@CrossOrigin(origins = "*")
public class SysAdminController {

    private final SysAdminService adminService;
    private final SysAdminMapper adminMapper;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<SysAdminDTO> addNewAdmin(@Valid @NotNull @RequestBody final NewSysAdminDTO dto) {
        final var admin = adminService.add(dto);
        return ResponseEntity.status(HttpStatus.OK).body(adminMapper.EntityToDTO(admin));
    }
}
