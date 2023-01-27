package com.isa.BloodTransferInstitute.exception.handler;

import com.isa.BloodTransferInstitute.exception.*;

import org.hibernate.procedure.ParameterStrategyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { NotFoundException.class, CreateAppointmentException.class, ScheduleException.class, UserHasNoRoleException.class, UsernameNotFoundException.class,MapException.class, ParameterStrategyException.class,FinishAppointmentException.class, CancelException.class, PastAppointmentException.class, PenaltyException.class })
	protected ResponseEntity<Object> handleException(BaseException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getStatus(), request);
	}
}
