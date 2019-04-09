package com.example.book.exceptions;

public class InvalidTokenException extends ParentException {
    private final static Integer STATUS = 403;
    public InvalidTokenException(String message, String error){
        super(error, message);
    }

    @Override
    public Integer getStatus() {
        return STATUS;
    }
}
