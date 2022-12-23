package com.isa.BloodTransferInstitute.mappers;

import com.isa.BloodTransferInstitute.dto.user.sysadmin.NewSysAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.sysadmin.SysAdminDTO;
import com.isa.BloodTransferInstitute.enums.Role;
import com.isa.BloodTransferInstitute.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SysAdminMapper {
    public User DTOtoEntity(NewSysAdminDTO dto) {
        final var updatedUser = User.builder()
                .deleted(false)
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .username(dto.getEmail())
                .password(dto.getPassword())
                .phoneNumber(dto.getPhoneNumber())
                .jmbg(dto.getJmbg())
                .gender(dto.getGender())
                .role(Role.SYSTEM_ADMIN)
                .build();
        return updatedUser;
    }
    public SysAdminDTO EntityToDTO(User admin) {
        final var dto = SysAdminDTO.builder()
                .id(admin.getId())
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .email(admin.getUsername())
                .jmbg(admin.getJmbg())
                .gender(admin.getGender())
                .role(admin.getRole())
                .phoneNumber(admin.getPhoneNumber())
                .build();
        return dto;
    }
}
