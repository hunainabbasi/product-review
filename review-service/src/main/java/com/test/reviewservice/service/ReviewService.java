package com.test.reviewservice.service;

import com.test.reviewservice.model.Review;

import java.util.Optional;

public interface ReviewService {

     Optional<Review> getReviewByProductId(String productId);

     Review save(Review review);

     void delete(String product_id);


}
