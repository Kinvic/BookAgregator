package com.bookagregator.service;

import com.bookagregator.entity.Book;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    public BookService(){}

    public List<Book> sortByPrice(){
        List<Book> list = new ArrayList<>();
        return list;
    }
}
