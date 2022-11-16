package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        var bloodBank = bankRepository.findById(dto.getBloodBankId()).get();
        var password = userRepository.findById(dto.getId()).get().getPassword();
        return userRepository.save(AdminMapper.UpdateDTOtoEntity(dto, password, bloodBank));
    }
}


