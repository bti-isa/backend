package com.isa.BloodTransferInstitute.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Addresses")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

	@Id
	@Column(nullable = false, unique = true, updatable = false)
	Long id;

	@Column(nullable = false)
	String city;

	@Column(nullable = false)
	String street;

	@Column(nullable = false)
	String country;

	@Column(nullable = false)
	Integer postalCode;

	@Column(nullable = false)
	String number;

	@OneToOne(mappedBy = "address")
	Location location;

	@OneToOne
	@JoinColumn(name = "user_id")
	User user;

	@OneToOne
	@JoinColumn(name = "bloodbank_id")
	BloodBank bloodBank;

}
