package com.example.book.mapper;

import com.example.book.dto.AuthorDto;
import com.example.book.models.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author authorDTOtoAuthor(AuthorDto dto);

    AuthorDto authorToAuthorDto(Author author);
}
