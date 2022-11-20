package com.isa.BloodTransferInstitute.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.BloodTransferInstitute.enums.AppointmentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(name = "Appointments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, updatable = false)
	Long id;

	@Column(nullable = false)
	LocalDateTime dateTime;

	@Column(nullable = false)
	Double duration;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", referencedColumnName = "id")
	User patient;

	@Column(nullable = false)
	Boolean finished;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "report_id", referencedColumnName = "id")
	Report report;

	@Column(nullable = false)
	AppointmentStatus status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bloodbank_id", referencedColumnName = "id")
	BloodBank bloodBank;
}
