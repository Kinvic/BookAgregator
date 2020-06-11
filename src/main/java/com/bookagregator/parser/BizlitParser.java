package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BizlitParser implements IStoreSearchParser{
    public BizlitParser(){}

    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try {
            Document document = Jsoup.connect("https://bizlit.com.ua/index.php?route=product/search&filter_name=" + name).userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("bk-wrap");
            for (Element e : elements) {
                Book book = new Book();
                book.setTitle(e.getElementsByClass("bk-name").text());
                book.setAuthor(e.getElementsByClass("bk-author").text());
                book.setURL(e.getElementsByClass("bk-wrap").select("a").attr("href"));
                book.setPrice(e.getElementsByClass("bk-price").text().replaceAll("[^\\d.]", ""));
                if(book.getPrice().isEmpty()) {
                    continue;
                }
                book.setImage(e.getElementsByAttributeValue("title", book.getTitle()).select("img").attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch(IOException e){}
        return books;
    }
}
