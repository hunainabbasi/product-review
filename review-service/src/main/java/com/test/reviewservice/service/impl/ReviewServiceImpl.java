package com.test.reviewservice.service.impl;

import com.test.reviewservice.model.Review;
import com.test.reviewservice.repository.ReviewRepository;
import com.test.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Optional<Review> getReviewByProductId(String product_Id) {
        Assert.notNull(product_Id, "The given id must not be null!");
        return reviewRepository.findReviewByProductId(product_Id);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.findReviewByProductId(review.getProductId())
                .map(dbObject -> {
                    dbObject.setNumberOfReviews(review.getNumberOfReviews());
                    dbObject.setProductId(review.getProductId());
                    dbObject.setAverageReviewScore(review.getAverageReviewScore());
                    return reviewRepository.save(dbObject);
                })
                .orElseGet(() -> {
                    return reviewRepository.save(review);
                });
    }

    @Override
    @Transactional
    public void delete(String product_id) {
        reviewRepository.deleteByProductId(product_id);
    }

}
