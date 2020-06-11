package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChytaykaParser implements IStoreSearchParser{
    public ChytaykaParser() {}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://chytayka.com.ua/catalogsearch/result/?q=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("item product product-item");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("product-item-link").text());
                book.setAuthor(e.getElementsByClass("author-link").select("a").attr("title"));
                book.setURL(e.getElementsByClass("product-item-link").attr("href"));
                book.setPrice(e.getElementsByClass("price-container price-final_price tax weee").text().replaceAll("[^\\d,]", "").replaceAll("[,]","."));
                book.setImage(e.getElementsByClass("product-image-wrapper").select("img").attr("data-src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
