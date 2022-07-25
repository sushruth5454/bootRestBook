package com.api.book.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private int id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Author author;
}
