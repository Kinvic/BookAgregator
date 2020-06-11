package com.bookagregator.controller;

import com.bookagregator.entity.Book;
import com.bookagregator.entity.Review;
import com.bookagregator.entity.Role;
import com.bookagregator.entity.User;
import com.bookagregator.parser.*;
import com.bookagregator.repo.ReviewRepository;
import com.bookagregator.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String search, Model model){
        List<Book> books;
        Pageable pageable;
        MainParser parser = new MainParser();
        if(search != null && !search.isEmpty()){
            books = parser.parse(search);
        }
        else {
            List<Book> list = new ArrayList<>();
            books = list;
        }

        model.addAttribute("books", books);
        model.addAttribute("search", search);

        return "main";
    }

}
