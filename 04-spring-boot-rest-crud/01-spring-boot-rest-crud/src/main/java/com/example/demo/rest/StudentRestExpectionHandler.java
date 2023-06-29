package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExpectionHandler {

//    add exception handling code here
    //    Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){

//        create a StudentErrorResponse
    StudentErrorResponse errorResponse = new StudentErrorResponse();

    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(exc.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

//        return ResponseEntity

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}

    //    add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e){

        //        create a StudentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

//        return ResponseEntity

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
