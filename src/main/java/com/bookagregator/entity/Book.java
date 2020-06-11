package com.bookagregator.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private String URL;
    private String price;
    private String image;


    public Book() {
    }

    public Book(String title, String author, String URL, String price, String image) {
        this.title = title;
        this.author = author;
        this.URL = URL;
        this.price = price;
        this.image = image;
    }


}
