package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class MapException extends BaseException {
    public MapException() {
        super("Service not work. Try again later.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value());
    }
}
