package com.isa.BloodTransferInstitute.model;

import com.isa.BloodTransferInstitute.enums.Gender;
import com.isa.BloodTransferInstitute.enums.Role;

import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
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
	String phoneNumber;

	@Column
	String occupation;

	@Column
	String education;

	@Column(nullable = false, unique = true)
	String jmbg;

	@Column(nullable = false)
	Gender gender;

	@Column(nullable = false)
	Role role;

	@Column
	Boolean accountActivated;

	@Column
	Integer penalties;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	Address address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bloodbank_id", referencedColumnName = "id")
	BloodBank bloodBank;
}
