package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.auth.ChangePasswordDTO;
import com.isa.BloodTransferInstitute.exception.NotFoundException;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public Optional<Boolean> changePassword(ChangePasswordDTO dto) {
        var user = userRepository.findByEmail(dto.getEmail());
        return Optional.ofNullable(executePasswordChange(user, dto.getPassword()).orElseThrow(NotFoundException::new));
    }

    private Optional<Boolean> executePasswordChange(User user, String newPassword){
        try{
            user.setPassword(newPassword);
            userRepository.save(user);
            return Optional.of(true);
        }catch (Exception e){
            return Optional.of(false);
        }
    }
}
