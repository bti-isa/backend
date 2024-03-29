package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
	public NotFoundException() {
		super("Entity/Entities not found!", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
	}
}
