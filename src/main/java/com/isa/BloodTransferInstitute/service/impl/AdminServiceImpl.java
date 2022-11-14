package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final BloodBankRepository bankRepository;

    @Override
    public User update(UpdateAdminDTO dto) {
        var bloodBank = bankRepository.findById(dto.getBloodBankId()).get();
        return userRepository.save(AdminMapper.UpdateDTOToEntity(dto, bloodBank));
    }
}


