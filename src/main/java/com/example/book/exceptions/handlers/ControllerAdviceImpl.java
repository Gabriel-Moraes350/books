package com.example.book.exceptions.handlers;

import com.example.book.exceptions.InvalidTokenException;
import com.example.book.exceptions.NotFoundException;
import com.example.book.exceptions.ParentException;
import com.example.book.exceptions.UriMalFormedException;
import com.example.book.exceptions.models.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerAdviceImpl {

    @ExceptionHandler(UriMalFormedException.class)
    public ResponseEntity<ResponseError> malFormedUriException(UriMalFormedException e){

        return ResponseEntity.badRequest().body(this.getResponseError(e));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> notFoundException(NotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.getResponseError(e));
    }

    private ResponseError getResponseError(ParentException e){
        return new ResponseError(e.getMessage(), e.getError(), e.getStatus());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ResponseError> tokenInvalid(InvalidTokenException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(this.getResponseError(e));
    }
}
