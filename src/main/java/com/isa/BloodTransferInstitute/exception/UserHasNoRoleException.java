package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class UserHasNoRoleException extends BaseException {
	public UserHasNoRoleException(final String message) {
		super(message, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
	}
}
