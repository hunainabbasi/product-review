package com.test.reviewservice.repository;

import com.test.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Optional<Review> findReviewByProductId(String productId);

    Optional<?> deleteByProductId(String productId);

}
