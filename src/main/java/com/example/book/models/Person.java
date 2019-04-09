package com.example.book.models;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@MappedSuperclass
@Data
public abstract class Person implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String  name;

    private String lastName;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "person_phones", joinColumns = @JoinColumn(name = "person_id"))
    private List<String> phones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
