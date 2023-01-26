package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.auth.ChangePasswordDTO;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {

    Optional<Boolean> changePassword(ChangePasswordDTO dto);

    UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException;

    Long getIdByEmail(String email);
}
