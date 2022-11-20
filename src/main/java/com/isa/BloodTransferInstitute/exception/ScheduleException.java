package com.isa.BloodTransferInstitute.exception;

public class ScheduleException extends RuntimeException {
	public ScheduleException() {
		super("The patient had an examination within the last six months.");
	}
}
