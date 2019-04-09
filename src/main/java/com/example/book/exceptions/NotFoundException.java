package com.example.book.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends ParentException{
    private final static Integer STATUS = 404;
    public NotFoundException(String message, String error){
        super(error, message);
    }

    @Override
    public Integer getStatus() {
        return STATUS;
    }
}
