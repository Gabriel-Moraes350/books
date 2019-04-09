package com.example.book.exceptions;

import lombok.*;

@NoArgsConstructor
@Getter
public abstract  class ParentException extends RuntimeException{
    @NonNull
    protected String error;

    @NonNull
    protected String message;
    protected Integer status;

    public ParentException(String error, String message){
        this.error = error;
        this.message = message;
    }

    public String getError(){
        return this.error;
    }

    public String getMessage(){
        return this.message;
    }
    public abstract Integer getStatus();

}
