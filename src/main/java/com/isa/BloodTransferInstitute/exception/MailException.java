package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class MailException extends BaseException {
	public MailException() {
		super("An error occurred while sending an email.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
}
