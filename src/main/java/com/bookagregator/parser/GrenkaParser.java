package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GrenkaParser implements IStoreSearchParser{
    public GrenkaParser() {}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://grenka.ua/search?k=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("xsrchl120 rel");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("y20").text());
                book.setAuthor(e.getElementsByClass("xsrchl57").get(0).text());
                book.setURL(e.getElementsByClass("y20").attr("href"));
                book.setPrice(e.getElementsByClass("xsrchl50").eachText().get(0).replaceAll("[^\\d.]", ""));
                book.setImage(e.getElementsByClass("xsrchl22").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
