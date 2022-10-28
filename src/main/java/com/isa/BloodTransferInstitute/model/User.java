package com.isa.BloodTransferInstitute.model;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false, unique = true)
	Long id;

	@Column(nullable = false)
	Boolean deleted;

	@Column(nullable = false)
	String firstname;

	@Column(nullable = false)
	String lastname;

	@Column(nullable = false, updatable = false, unique = true)
	String email;

	@Column(nullable = false)
	String password;

	@Column(nullable = false, unique = true)
	String jmbg;

	@Column(nullable = false)
	Gender gender;

	@Column(nullable = false)
	Role role;

	@Column(nullable = false)
	Boolean active;

	@Column(nullable = false)
	Integer penalties;

	@Column
	@OneToMany(mappedBy = "patient")
	List<Appointment> appointments;

}
