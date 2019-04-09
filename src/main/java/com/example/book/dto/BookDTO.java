package com.example.book.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO implements Serializable {

    private Long id;

    private String name;

    private LocalDate date;

    private Integer pages;

    private List<AuthorDto> authors;

    @Override
    public String toString() {
        return "dto";
    }
}
