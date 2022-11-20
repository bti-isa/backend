package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.repository.dto.user.admin.NewAdminDTO;
import com.isa.BloodTransferInstitute.repository.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.model.User;

public interface AdminService {
    User update(UpdateAdminDTO dto);
    User add(NewAdminDTO dto);
}
