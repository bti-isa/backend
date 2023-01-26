package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.user.admin.AdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/Admin")
public class AdminController {
    private final AdminService adminService;
    private final GetUserMapper getUserMapper;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public ResponseEntity<AdminDTO> addNewAdmin(@Valid @NotNull @RequestBody final NewAdminDTO dto) {
        final var admin = adminService.add(dto);
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.AdminToAdminDTO(admin));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN','INSTITUTE_ADMIN')")
    public ResponseEntity<AdminDTO> update(@Valid @NonNull @RequestBody final UpdateAdminDTO dto){
        final var updatedUser = adminService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.AdminToAdminDTO(updatedUser));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins(){
        var result = adminService.getAll();
        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.adminListToDTOlist(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getById(@Valid @NotNull @PathVariable("id") final Long id){
        var result = adminService.getById(id);
        if(result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(getUserMapper.AdminToAdminDTO(result.get()));
    }
}
