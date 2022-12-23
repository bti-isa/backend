package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.user.sysadmin.NewSysAdminDTO;
import com.isa.BloodTransferInstitute.model.User;

public interface SysAdminService {
    User add(NewSysAdminDTO dto);
}
