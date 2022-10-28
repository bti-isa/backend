package com.isa.BloodTransferInstitute.enums;

public enum Role {

	SYSTEM_ADMIN("SYSTEM_ADMIN"),
	INSTITUTE_ADMIN("INSTITUTE_ADMIN"),
	PATIENT("PATIENT");

	private final String role;

	Role(final String role) { this.role = role; }

	public String getRole() { return this.role; }
}
