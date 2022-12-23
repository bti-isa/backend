package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.sysadmin.NewSysAdminDTO;
import com.isa.BloodTransferInstitute.mappers.SysAdminMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.security.UserAuthenticationProvider;
import com.isa.BloodTransferInstitute.service.SysAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAdminServiceImpl implements SysAdminService {
    private final UserRepository userRepository;
    private final SysAdminMapper sysAdminMapper;
    @Autowired
    private UserAuthenticationProvider passwordEncoder;

    @Override
    public User add(NewSysAdminDTO dto) {
        User admin = sysAdminMapper.DTOtoEntity(dto);
        admin.setPassword(passwordEncoder.passwordEncoder().encode(dto.getPassword()));
        admin.setEnabled(true);
        return userRepository.save(admin);
    }
}
