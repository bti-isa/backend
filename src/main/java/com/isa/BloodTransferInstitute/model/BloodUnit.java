package com.isa.BloodTransferInstitute.model;

import com.isa.BloodTransferInstitute.enums.BloodType;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BloodUnits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, unique = true)
	Long id;

	@Column(nullable = false, unique = true)
	BloodType bloodType;

	@Column(nullable = false)
	int quantity;

	@ManyToMany(mappedBy = "bloodUnits")
	List<BloodBank> bloodBanks;

	public BloodUnit(Integer bloodQuantity, BloodType patientBloodType) {
		bloodType = patientBloodType;
		quantity = bloodQuantity;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bloodbank_id", referencedColumnName = "id")
	BloodBank bloodBank;
}
