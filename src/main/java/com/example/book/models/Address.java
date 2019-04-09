package com.example.book.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(of = {"id"})
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String street;

    private String city;

    private Integer number;

    @Column(length = 2)
    private String uf;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author owner;



}
