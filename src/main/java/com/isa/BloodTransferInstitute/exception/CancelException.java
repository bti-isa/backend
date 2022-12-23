package com.isa.BloodTransferInstitute.exception;


import org.springframework.http.HttpStatus;

public class CancelException extends BaseException {
    public CancelException() {
        super("You cannot cancel an appointment that starts in less than 24 hours.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }
}
