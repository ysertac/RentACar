package com.etiya.rentacar.core.exceptions.handlers;

import com.etiya.rentacar.core.exceptions.problemDetails.BusinessProblemDetails;
import com.etiya.rentacar.core.exceptions.problemDetails.ProblemDetails;
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
        List<Map<String, String>> validationExceptionList = new ArrayList<>();
        exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(fieldError.getField(), fieldError.getDefaultMessage());
                    validationExceptionList.add(error);
                    return validationExceptionList;
                }).collect(Collectors.toList());
        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
        validationProblemDetails.setErrors(validationExceptionList);
        return validationProblemDetails;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetails handleEntityNotFoundException(RuntimeException exception) {

        ProblemDetails problemDetails = new ProblemDetails();
        problemDetails.setStatus(HttpStatus.BAD_REQUEST.toString());
        problemDetails.setDetail(exception.getClass().getSimpleName());
        problemDetails.setType("http://etiya.com/exceptions/other");
        problemDetails.setTitle("Other Exception");
        return problemDetails;
    }
}