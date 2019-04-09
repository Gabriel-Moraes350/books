package com.example.book.services.impl;

import com.example.book.controllers.BookController;
import com.example.book.dto.BookDTO;
import com.example.book.exceptions.NotFoundException;
import com.example.book.exceptions.UriMalFormedException;
import com.example.book.mapper.BookMapper;
import com.example.book.models.Book;
import com.example.book.repositories.BookRepository;
import com.example.book.services.BookService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
@Log4j2
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.books}")
    private String queue;

    @Autowired
    private BookMapper mapper;

    @Override
    public URI insert(Book book) {
        URI uri = null;
        try {
            book.getAuthors().forEach(i -> {
                i.getBooks().add(book);
            });
            Book newBook = bookRepository.save(book);
            this.sendMessage(newBook);
            log.debug(book.toString());
            uri = MvcUriComponentsBuilder.fromController(BookController.class).path("/{id}").buildAndExpand(newBook.getId()).toUri();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UriMalFormedException("Erro ao formar link", "Não foi possível gerar um URL para visualizar retorno da API");
        }

        return uri;
    }

    private void sendMessage(Book newBook) {
        jmsTemplate.convertAndSend(queue, newBook);
    }


    @Override
    public BookDTO findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return mapper.bookToBookDTO(book.orElseThrow(() -> new NotFoundException("Não foi possível encontrar o livro", "")));
    }

    @Override
    public Page<BookDTO> list(Pageable pageable) {
        Page<Book> pages = bookRepository.findAll(pageable);
        return pages.map(mapper::bookToBookDTO);
    }
}
