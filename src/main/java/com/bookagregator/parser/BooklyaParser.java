package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooklyaParser implements IStoreSearchParser{
    public BooklyaParser(){}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://www.booklya.ua/search/?q=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("goodsItem goodsItemWidth  ");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("goodsItem-t").text());
                book.setAuthor(e.getElementsByClass("MB_authorName").text());
                book.setURL(e.getElementsByClass("goodsItem-t").attr("href"));
                if(e.getElementsByClass("goodsItem-ba-Price").text().isEmpty()){
                    book.setPrice(e.getElementsByClass("goodsItem-ba-SinglePrice").text().replaceAll("[^\\d.]", ""));
                }
                else {
                    book.setPrice(e.getElementsByClass("goodsItem-ba-Price").text().replaceAll("[^\\d.]", ""));
                }
                book.setImage(e.getElementsByClass("goodsItemImg").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
