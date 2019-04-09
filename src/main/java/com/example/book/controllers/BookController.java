package com.example.book.controllers;

import com.example.book.dto.BookDTO;
import com.example.book.mapper.BookMapper;
import com.example.book.models.Book;
import com.example.book.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api("Books")
@RestController
@RequestMapping("/books")
@Log4j2
@CrossOrigin
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<BookDTO>> listar(Pageable pageable){
        return ResponseEntity.ok(service.list(pageable).getContent());
    }

    @ApiOperation("Inserção de livros")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Book book){

        URI uri = service.insert(book);
        log.info("Inserido book");
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> view(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
