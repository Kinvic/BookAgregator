package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LavkababuinParser implements IStoreSearchParser{
    public LavkababuinParser() {}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://lavkababuin.com/index.php?route=product/search&search=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("product-card col-mob-full col-6 col-sm-4 col-md-3 col-lg-3 pl-0 pr-0 product__item");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("title-product").attr("title"));
                book.setAuthor(e.getElementsByClass("product__author").text());
                book.setURL(e.getElementsByClass("title-product").attr("href"));
                book.setPrice(e.getElementsByClass("price-wrapper ").text().replaceAll("[^\\d]", ""));
                if(book.getPrice().isEmpty()) {
                    continue;
                }
                book.setImage(e.getElementsByClass("img-alt-fluid").select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
