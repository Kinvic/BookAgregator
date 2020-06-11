package com.bookagregator.controller;

import com.bookagregator.entity.Review;
import com.bookagregator.entity.User;
import com.bookagregator.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String showAll(Model model){
        Iterable<Review> reviews = reviewRepository.findAll();

        model.addAttribute("reviews",reviews);
        return "reviews";
    }

    @PostMapping("/add")
    public String addReview(@AuthenticationPrincipal User user,
                            @RequestParam String title,
                            @RequestParam String text, Map<String, Object> model,
                            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Review review = new Review(title, text, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            review.setFilename(resultFilename);
        }

        reviewRepository.save(review);

        Iterable<Review> reviews = reviewRepository.findAll();

        model.put("reviews", reviews);

        return "reviews";
    }

}
