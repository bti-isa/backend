package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AdminService;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final BloodBankRepository bankRepository;

    public User add(NewAdminDTO dto) {
        return userRepository.save(adminMapper.DTOtoEntity(dto));
    }

    @Override
    public User update(final UpdateAdminDTO dto) {
        BloodBank bloodBank = null;
        if(dto.getBloodBankId() != null) {
            bloodBank = bankRepository.findById(dto.getBloodBankId()).get();
        }
        var password = userRepository.findById(dto.getId()).get().getPassword();
        return userRepository.save(AdminMapper.UpdateDTOtoEntity(dto, password, bloodBank));
    }
}


