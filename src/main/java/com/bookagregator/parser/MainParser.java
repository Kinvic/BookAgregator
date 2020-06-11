package com.bookagregator.parser;

import com.bookagregator.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class MainParser {
    public MainParser() {}

    public List<Book> parse(String name){
        List<Book> books = new ArrayList<>();
        books.addAll(new BizlitParser().parse(name));
        books.addAll(new Book24Parser().parse(name));
        books.addAll(new BooklyaParser().parse(name));
        books.addAll(new BookzoneParser().parse(name));
        books.addAll(new BukvaParser().parse(name));
        books.addAll(new ChytaykaParser().parse(name));
        books.addAll(new GrenkaParser().parse(name));
        books.addAll(new KnigogradParser().parse(name));
        books.addAll(new LavkababuinParser().parse(name));
        books.addAll(new YakabooParser().parse(name));
        return books;
    }
}
