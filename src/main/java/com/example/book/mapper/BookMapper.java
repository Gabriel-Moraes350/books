package com.example.book.mapper;

import com.example.book.dto.BookDTO;
import com.example.book.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);

    Book bookDTOtoBook(BookDTO book);
}
