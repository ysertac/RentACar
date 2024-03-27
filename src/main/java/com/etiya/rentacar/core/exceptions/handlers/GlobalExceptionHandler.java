package com.etiya.rentacar.core.exceptions.handlers;

import com.etiya.rentacar.core.exceptions.problemDetails.BusinessProblemDetails;
import com.etiya.rentacar.core.exceptions.problemDetails.ValidationProblemDetails;
import com.etiya.rentacar.core.exceptions.types.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException businessException) {
        BusinessProblemDetails businessProblemDetails = new BusinessProblemDetails();
        businessProblemDetails.setDetail(businessException.getMessage());
        return businessProblemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> validationExceptions = new HashMap<>();
        List<String> validationError = new ArrayList<>();
        exception.getFieldErrors().stream()
                .map(fieldError -> {
                    validationError.add(fieldError.getDefaultMessage());
                    validationExceptions.put(fieldError.getField(), validationError);
                    return validationExceptions;
                }).collect(Collectors.toList());
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationExceptions);
        return validationProblemDetails;
    }
}
