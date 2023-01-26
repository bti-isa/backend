package com.isa.BloodTransferInstitute.exception;

import org.springframework.http.HttpStatus;

public class NoEquipmentException extends BaseException{
    public NoEquipmentException(){
        super("Insufficient equipment.", HttpStatus.EXPECTATION_FAILED, HttpStatus.EXPECTATION_FAILED.value());
    }
}
