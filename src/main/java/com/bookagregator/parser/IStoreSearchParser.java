package com.bookagregator.parser;

import com.bookagregator.entity.Book;

import java.util.List;

public interface IStoreSearchParser {
    public List<Book> parse(String name);
}
