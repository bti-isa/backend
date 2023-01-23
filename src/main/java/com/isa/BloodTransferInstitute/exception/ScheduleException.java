package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class ScheduleException extends BaseException {
	public ScheduleException() {
		super("An error occurred while scheduling an appointment.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
}
