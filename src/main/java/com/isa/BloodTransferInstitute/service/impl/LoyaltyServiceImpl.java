package com.isa.BloodTransferInstitute.service.impl;

import com.isa.BloodTransferInstitute.dto.loyalty.LoyaltyDTO;
import com.isa.BloodTransferInstitute.model.Loyalty;
import com.isa.BloodTransferInstitute.repository.LoyaltyRepository;
import com.isa.BloodTransferInstitute.service.LoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoyaltyServiceImpl implements LoyaltyService {
    private final LoyaltyRepository loyaltyRepository;

    @Override
    public Loyalty update(LoyaltyDTO dto) {
        List<Loyalty> all = loyaltyRepository.findAll();
        if(all.isEmpty()) {
            Loyalty loyalty = new Loyalty(dto);
            loyaltyRepository.save(loyalty);
            return loyalty;
        }
        else {
            Loyalty loyalty = all.get(0);
            loyalty.setSilver(dto.getSilver());
            loyalty.setGold(dto.getGold());
            loyalty.setPlatinum(dto.getPlatinum());
            loyalty.setSilverPercentage(dto.getSilverPercentage());
            loyalty.setGoldPercentage(dto.getGoldPercentage());
            loyalty.setPlatinumPercentage(dto.getPlatinumPercentage());
            loyalty.setSilverPartner(dto.getSilverPartner());
            loyalty.setGoldPartner(dto.getGoldPartner());
            loyalty.setPlatinumPartner(dto.getPlatinumPartner());
            loyalty.setPoints(dto.getPoints());
            loyaltyRepository.save(loyalty);
            return loyalty;
        }
    }
}
