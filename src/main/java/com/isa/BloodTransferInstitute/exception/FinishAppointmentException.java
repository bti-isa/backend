package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class FinishAppointmentException extends BaseException {
    public FinishAppointmentException() {
        super("Institute admin has more appointments in the same time.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }
}