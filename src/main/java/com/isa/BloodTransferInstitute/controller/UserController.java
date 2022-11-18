package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.auth.ChangePasswordDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AdminService;
import com.isa.BloodTransferInstitute.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/User")
@CrossOrigin(origins = "*")
public class UserController {

    private final AuthServiceImpl authService;

    @PutMapping("/password/change")
    public ResponseEntity<Boolean> changePassword(@Valid @NotNull @RequestBody final ChangePasswordDTO dto){
        var result = authService.changePassword(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
