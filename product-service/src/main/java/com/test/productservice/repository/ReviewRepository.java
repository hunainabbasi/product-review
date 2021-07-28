package com.test.productservice.repository;

import com.test.productservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Optional<Review> findByProductId(String product_id);
}
