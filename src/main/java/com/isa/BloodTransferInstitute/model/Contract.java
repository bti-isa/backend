package com.isa.BloodTransferInstitute.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contract")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, unique = true)
	Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bloodunit_id", referencedColumnName = "id")
	BloodUnit bloodUnit;

	@Column(nullable = false)
	LocalDate date;

	@Column(nullable = false)
	int quantity;

	public boolean canDeliver() {
		return bloodUnit.getQuantity() - quantity >= 0;
	}
}
