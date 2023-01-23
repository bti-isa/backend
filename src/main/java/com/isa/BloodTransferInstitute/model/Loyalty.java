package com.isa.BloodTransferInstitute.model;

import com.isa.BloodTransferInstitute.dto.loyalty.LoyaltyDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Loyalty")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loyalty {
    public Loyalty(LoyaltyDTO dto) {
        silver = dto.getSilver();
        gold = dto.getGold();
        platinum = dto.getPlatinum();
        silverPercentage = dto.getSilverPercentage();
        goldPercentage = dto.getGoldPercentage();
        platinumPercentage = dto.getPlatinumPercentage();
        silverPartner = dto.getSilverPartner();
        goldPartner = dto.getGoldPartner();
        platinumPartner = dto.getPlatinumPartner();
        points = dto.getPoints();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false,unique = true)
    Long id;

    @Column(nullable = false)
    Integer platinum;

    @Column(nullable = false)
    Integer gold;

    @Column(nullable = false)
    Integer silver;

    @Column(nullable = false)
    Integer silverPercentage;

    @Column(nullable = false)
    Integer goldPercentage;

    @Column(nullable = false)
    Integer platinumPercentage;

    @Column(nullable = false)
    String silverPartner;

    @Column(nullable = false)
    String goldPartner;

    @Column(nullable = false)
    String platinumPartner;

    @Column(nullable = false)
    Integer points;
}
