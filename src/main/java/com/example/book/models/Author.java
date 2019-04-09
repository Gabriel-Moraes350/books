package com.example.book.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends Person{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_books", joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonIgnore
    private Set<Book> books = new HashSet<>();


    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Address address;

    @Column(length = 20)
    private String document;

    private LocalDate birthDate;
}
