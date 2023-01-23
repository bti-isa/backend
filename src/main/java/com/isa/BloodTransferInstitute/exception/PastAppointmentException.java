package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class PastAppointmentException extends BaseException{
    public PastAppointmentException() {
        super("Appointment is finished.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }
}
