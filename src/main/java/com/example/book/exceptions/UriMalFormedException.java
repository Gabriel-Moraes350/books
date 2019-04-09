package com.example.book.exceptions;

public class UriMalFormedException extends ParentException{
    private final static Integer STATUS = 500;
    public UriMalFormedException(String message, String error){
        super(error, message);
    }

    @Override
    public Integer getStatus() {
        return STATUS;
    }
}
