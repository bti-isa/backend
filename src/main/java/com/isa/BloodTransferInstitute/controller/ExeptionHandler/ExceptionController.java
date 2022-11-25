//package com.isa.BloodTransferInstitute.controller.ExeptionHandler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.StreamSupport;
//
//import static java.util.stream.Collectors.toList;
//
//@Slf4j
//@ControllerAdvice
//@RestController
//public class ExceptionController {
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//    @ExceptionHandler(ConstraintViolationException.class)
//    public List<ValidationErrors>
//    handleConstraintViolation(ConstraintViolationException ex) {
//        log.debug(
//                "Constraint violation exception encountered: {}",
//                ex.getConstraintViolations(),
//                ex
//        );
//        return buildValidationErrors(ex.getConstraintViolations());
//    }
//
//    private List<ValidationErrors> buildValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
//            return constraintViolations.
//                    stream()
//                    .map(violation -> ValidationErrors.builder()
//                                                     .field(StreamSupport.stream(
//                                                            violation.getPropertyPath().spliterator(), false).
//                                                    reduce((first, second) -> second).
//                                                    orElse(null).toString())
//                                                    .error(violation.getMessage())
//                                                    .build())
//                                                    .collect(toList());
//        }
//    }
//}
