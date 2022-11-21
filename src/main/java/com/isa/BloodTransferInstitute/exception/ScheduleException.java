package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class ScheduleException extends BaseException {
	public ScheduleException() {
		super("The patient had an examination within the last six months or the patient already has scheduled examination.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
}
