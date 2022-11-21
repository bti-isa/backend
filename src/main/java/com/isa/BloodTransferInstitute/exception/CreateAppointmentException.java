package com.isa.BloodTransferInstitute.exception;

public class CreateAppointmentException extends RuntimeException {
	public CreateAppointmentException() {
		super("Appointment in same date&time and blood bank already exists!");
	}
}
