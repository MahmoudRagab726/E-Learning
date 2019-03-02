package com.three2one.exception;

import com.three2one.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandellerComponent extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralFailureException.class)
    public ResponseEntity<Response> handleException(GeneralFailureException ex, WebRequest webRequest){
        Response response = new Response();
        response.setStatusCode(ex.getStatusCode().getCode());
        response.setResponseMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({Exception.class,RuntimeException.class})
    public ResponseEntity<Response> handleGeneralException(Exception ex, WebRequest webRequest){
        Response response = new Response();
        response.setStatusCode("99");
        response.setResponseMessage("General Failure Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
