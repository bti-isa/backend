package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.user.admin.UpdateAdminDTO;
import com.isa.BloodTransferInstitute.model.User;

public interface AdminService {
    User update(UpdateAdminDTO dto);
}
