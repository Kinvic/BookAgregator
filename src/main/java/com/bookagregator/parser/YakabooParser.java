package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YakabooParser implements IStoreSearchParser{

    public YakabooParser() {
    }

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.yakaboo.ua/search/?multi=0&cat=&q=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("item last");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("full-name").text());
                book.setAuthor(e.getElementsByClass("product-author").text());
                book.setURL(e.getElementsByClass("product-name").attr("href"));
                book.setPrice(e.getElementsByClass("price").eachText().get(0).replaceAll("[^\\d.]", ""));
                book.setImage(e.getElementsByClass("thumbnail product-image").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
