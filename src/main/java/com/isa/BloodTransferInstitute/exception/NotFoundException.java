package com.isa.BloodTransferInstitute.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException() {
		super("Entity/Entites not found!");
	}
}
