package com.isa.BloodTransferInstitute.dto.loyalty;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoyaltyDTO {
    @NotNull
    Integer platinum;

    @NotNull
    Integer gold;

    @NotNull
    Integer silver;

    @NotNull
    Integer silverPercentage;

    @NotNull
    Integer goldPercentage;

    @NotNull
    Integer platinumPercentage;

    @NotNull
    String silverPartner;

    @NotNull
    String goldPartner;

    @NotNull
    String platinumPartner;

    @NotNull
    Integer points;
}
