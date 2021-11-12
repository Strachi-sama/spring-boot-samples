package com.example.demo.library;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class LibraryUserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String lastName;
    private String email;

    @JsonManagedReference
    @OneToMany(
        mappedBy = "user", 
        cascade = CascadeType.ALL, 
        fetch = javax.persistence.FetchType.EAGER,
        orphanRemoval = true)
            private List<LibraryBookEntity> books;
    
    public LibraryUserEntity() {
        this.books = new java.util.ArrayList<LibraryBookEntity>();
    }

    public LibraryUserEntity(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.books = new java.util.ArrayList<LibraryBookEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LibraryBookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<LibraryBookEntity> books) {
        this.books = books;
    }

    public void addBook(LibraryBookEntity book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        String myBooks = this.books.stream()
            .map(book -> book.getTitle())
            .reduce("", (acc, title) -> acc + title + ", ");
        return "LibraryUserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", books=" + myBooks +
                '}';

    }
}