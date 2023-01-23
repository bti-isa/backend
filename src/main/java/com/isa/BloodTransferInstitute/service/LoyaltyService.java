package com.isa.BloodTransferInstitute.service;

import com.isa.BloodTransferInstitute.dto.loyalty.LoyaltyDTO;
import com.isa.BloodTransferInstitute.model.Loyalty;

public interface LoyaltyService {
    Loyalty update(LoyaltyDTO dto);
}
