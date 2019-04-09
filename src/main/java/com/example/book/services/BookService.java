package com.example.book.services;

import com.example.book.dto.BookDTO;
import com.example.book.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface BookService {
    URI insert(Book book);
    BookDTO findById(Long id);
    Page<BookDTO> list(Pageable pageable);
}
