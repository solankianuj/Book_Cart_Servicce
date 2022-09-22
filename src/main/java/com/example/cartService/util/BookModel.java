package com.example.cartService.util;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
public class BookModel {
    @Id
    @GenericGenerator(name = "bookData",strategy = "increment")
    @GeneratedValue(generator = "bookData")
    private Long bookId;
    private String bookName;
    private String author;
    private String description;
    private byte[] bookLogo;
    private double price;
    private int quantity;

}
