package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;



public class PenaltyException extends BaseException {
    public PenaltyException() {
        super("The patient has 3 penalties.", HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }
}