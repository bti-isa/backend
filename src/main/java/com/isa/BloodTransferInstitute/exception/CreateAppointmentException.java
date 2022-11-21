package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class CreateAppointmentException extends BaseException {
	public CreateAppointmentException() {
		super("Appointment in same date&time and blood bank already exists!", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
	}
}
