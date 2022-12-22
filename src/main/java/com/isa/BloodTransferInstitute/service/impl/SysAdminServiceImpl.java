package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.sysadmin.NewSysAdminDTO;
import com.isa.BloodTransferInstitute.mappers.SysAdminMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.SysAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAdminServiceImpl implements SysAdminService {
    private final UserRepository userRepository;
    private final SysAdminMapper sysAdminMapper;

    @Override
    public User add(NewSysAdminDTO dto) {
        return userRepository.save(sysAdminMapper.DTOtoEntity(dto));
    }
}
