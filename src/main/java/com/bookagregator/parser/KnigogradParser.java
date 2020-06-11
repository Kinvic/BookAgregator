package com.bookagregator.parser;

import com.bookagregator.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnigogradParser implements IStoreSearchParser {
    @Override
    public List<Book> parse(String name) {
        List<Book> books = new ArrayList<>();
        try{
            Document document = Jsoup.connect("http://www.knigograd.com.ua/index.php?subcats=Y&type=extended&status=A&pshort=N&pfull=N&pname=Y&pkeywords=N&cid=0&q=" + name + "&x=0&y=0&dispatch=products.search").userAgent("Chrome/83.0.4103.61 Safari/537.36").get();
            Elements elements = document.getElementsByClass("product-description");

            for (Element e : elements){
                String id = e.getElementsByAttributeValueMatching("name", "product_data").attr("value");
                Book book = new Book();
                book.setTitle(e.getElementsByClass("product-title").text());
                book.setAuthor(e.getElementsByAttributeValue("style", "color:#000000;").text());
                book.setURL("http://www.knigograd.com.ua/" + e.getElementsByClass("product-title").attr("href"));
                book.setPrice(e.getElementsByAttributeValueMatching("id","^sec_discounted_price").text().replaceAll("[^\\d.]", ""));
                book.setImage("http://www.knigograd.com.ua/" + document.getElementById("det_img_" + id).attr("src"));
                books.add(book);
                if(books.size() == 4) break;
            }
        }
        catch (IOException e){}

        return books;
    }
}
