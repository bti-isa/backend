package com.isa.BloodTransferInstitute.controller;

import com.isa.BloodTransferInstitute.dto.auth.AuthenticationRequestDto;
import com.isa.BloodTransferInstitute.dto.auth.ChangePasswordDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AdminService;
import com.isa.BloodTransferInstitute.security.JwtUtils;
import com.isa.BloodTransferInstitute.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(requestDto.getUsername());
        if (user != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @PutMapping("/password/change")
    @PreAuthorize("hasAnyAuthority('INSTITUTE_ADMIN', 'SYSTEM_ADMIN', 'PATIENT')")
    public ResponseEntity<Boolean> changePassword(@Valid @NotNull @RequestBody final ChangePasswordDTO dto){
        var result = authService.changePassword(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }
}
