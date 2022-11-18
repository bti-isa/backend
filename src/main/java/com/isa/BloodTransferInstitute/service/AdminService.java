package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    User update(UpdateAdminDTO dto);
    User add(NewAdminDTO dto);
    List<User> getAll();
    Optional<User> getById(Long id);
}
