package com.example.book.exceptions.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseError implements Serializable {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public ResponseError(String message, String error, Integer status) {
        this.message = message;
        this.path = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri().getPath();
        this.timestamp = ZonedDateTime.now(ZoneId.of("GMT")).format(DATEFORMAT);
        this.error = error;
        this.status = status;
    }
}
