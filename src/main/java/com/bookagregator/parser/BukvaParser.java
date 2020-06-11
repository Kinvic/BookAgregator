package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BukvaParser implements IStoreSearchParser{

    public BukvaParser(){

    }

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        int counter = 0;
        try {
            Document document = Jsoup.connect("https://bukva.ua/ua/search/index/?l=1&search=" + name + "&filter=Array&sort=default&page=1").userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByAttributeValueMatching("class", "no\\d");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("h4").select("a").text());
                book.setAuthor(e.getElementsByClass("h4").select("span").text());
                book.setURL(e.getElementsByClass("h4").select("a").attr("href"));
                book.setPrice(e.getElementsByClass("price_value_left").eachText().get(0).replaceAll("[^\\d.]", ""));
                book.setImage(e.getElementsByClass("col-sm-2").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
