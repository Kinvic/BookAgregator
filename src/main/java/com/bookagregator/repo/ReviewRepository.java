package com.bookagregator.repo;

import com.bookagregator.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    List<Review> findByTitleContains(String title);
}
