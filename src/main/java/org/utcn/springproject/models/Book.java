package org.utcn.springproject.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Book extends AbstractEntity {
    @NotBlank(message = "Title is required!")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "Author is required!")
    @Size(min = 3, max = 50, message = "Author must be between 3 and 50 characters")
    private String author;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishedDate;

    @NotNull(message = "Quantity is required!")
    private Long quantity;

    @NotNull(message = "Price is required!")
    private Double price;

    private BookType type;

    public Book(String title, String author, LocalDate publishedDate, Long quantity, Double price, BookType type) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public Book() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return title;
    }

}
