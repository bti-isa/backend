package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.repository.dto.auth.ChangePasswordDTO;

import java.util.Optional;

public interface AuthService {

    Optional<Boolean> changePassword(ChangePasswordDTO dto);
}
