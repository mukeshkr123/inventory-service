package com.programmingmukesh.inventory.exceptions;

import com.programmingmukesh.inventory.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<BaseResponse<String>> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        BaseResponse<String> errorResponse = BaseResponse.error(ex.getMessage()); // Using BaseResponse
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleGenericException(Exception ex) {
        BaseResponse<String> errorResponse = BaseResponse.error("Internal Server Error"); // Using BaseResponse
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
