package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book24Parser implements IStoreSearchParser{
    public Book24Parser() {}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://book24.ua/catalog/?q=" + name + "&submit=Найти").userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("catalog-item-card");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("item-title").attr("title"));
                book.setAuthor(e.getElementsByClass("article").text());
                book.setURL("https://book24.ua/" + e.getElementsByClass("item-title").attr("href"));
                book.setPrice(e.getElementsByClass("catalog-item-price").text().replaceAll("[^\\d]", ""));
                book.setImage("https://book24.ua/" + e.getElementsByClass("item_img").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
