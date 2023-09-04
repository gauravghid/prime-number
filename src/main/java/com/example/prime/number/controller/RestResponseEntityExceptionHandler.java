package com.example.prime.number.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * method to handle MethodArgumentTypeMismatchException exception
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail methodArgumentTypeMismatchErrorHandler(MethodArgumentTypeMismatchException exception)
    {
        ProblemDetail problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Please provide valid input URI params");
        problemDetail.setTitle("Input provided is not valid!!");
        problemDetail.setType(URI.create("http://primes/errors/not-found"));
        return problemDetail;
    }

    /**
     * method to handle IllegalArgumentException exception
     * @param exception
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail illegalArgumentException(IllegalArgumentException exception)
    {
        ProblemDetail problemDetail= ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Please provide one of the supported value(BFA/SA) or empty");
        problemDetail.setTitle("Algorithm supplied is not valid !!");
        problemDetail.setType(URI.create("http://primes/errors/not-found"));
        return problemDetail;
    }

}
