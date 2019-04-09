package com.example.book.dto;

import com.example.book.models.Address;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorDto {
    private Address address;

    private String document;

    private LocalDate birthDate;

    private String  name;

    private String lastName;
}
