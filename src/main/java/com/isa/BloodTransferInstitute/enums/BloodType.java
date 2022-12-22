package com.isa.BloodTransferInstitute.enums;

public enum BloodType {

	A_PLUS("A+"),
	B_PLUS("B+"),
	AB_PLUS("AB+"),
	O_PLUS("O+"),
	A_MINUS("A-"),
	B_MINUS("B-"),
	AB_MINUS("AB-"),
	O_MINUS("O-");

	private final String bloodType;
	BloodType(final String bloodType) { this.bloodType = bloodType; }
	public String getBloodType() { return this.bloodType; }
}
