package com.isa.BloodTransferInstitute.service.impl;
import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.mappers.AdminMapper;
import com.isa.BloodTransferInstitute.mappers.GetUserMapper;
import com.isa.BloodTransferInstitute.mappers.PatientMapper;
import com.isa.BloodTransferInstitute.model.BloodBank;
import com.isa.BloodTransferInstitute.model.User;
import com.isa.BloodTransferInstitute.repository.BloodBankRepository;
import com.isa.BloodTransferInstitute.repository.UserRepository;
import com.isa.BloodTransferInstitute.security.UserAuthenticationProvider;
import com.isa.BloodTransferInstitute.service.AdminService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminMapper adminMapper;
    private final BloodBankRepository bankRepository;

    @Autowired
    private UserAuthenticationProvider passwordEncoder;

    public User add(NewAdminDTO dto) {
        User user = adminMapper.DTOtoEntity(dto);
        user.setPassword(passwordEncoder.passwordEncoder().encode(dto.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findByRole(Role.INSTITUTE_ADMIN);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
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


