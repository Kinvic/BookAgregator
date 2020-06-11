package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookzoneParser implements IStoreSearchParser{
    public BookzoneParser(){}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://bookzone.com.ua/search/?q=" + name + "&view=list").userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("BrowseEntry");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("js_name").text());
                book.setAuthor(e.getElementsByClass("BrowseMeta-byline").text());
                book.setURL("https://bookzone.com.ua" + e.getElementsByClass("js_name").attr("href"));
                book.setPrice(e.getElementsByClass("BrowsePricing-price").text().replaceAll("[^\\d.]", ""));
                if(book.getPrice().isEmpty()) {
                    continue;
                }
                book.setImage("https://bookzone.com.ua" + e.getElementsByClass("js_main_photo").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
