package com.test.reviewservice;

import com.test.reviewservice.model.Review;
import com.test.reviewservice.service.ReviewService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ReviewServiceApplicationTests {

    @Autowired
    ReviewService reviewService;

    @Test
    public void addReview() throws EntityNotFoundException {
        Review review = getReview();
        Review reviewOptional = reviewService.save(review);
        Assert.assertNotNull(reviewOptional);
    }

    @Test
    public void addAndUpdateReview() throws EntityNotFoundException {
        Review reviewObject = getReview();
        Review review = reviewService.save(reviewObject);
        Assert.assertNotNull(review);
        Integer numberOfReviewsUpdate = 100;
        review.setNumberOfReviews(numberOfReviewsUpdate);
        Review updatedObject = reviewService.save(review);
        assertEquals(review.getId(), updatedObject.getId());
        assertEquals(updatedObject.getNumberOfReviews(), numberOfReviewsUpdate);

    }

    @Test
    public void getCall() throws EntityNotFoundException {
        Review reviewObject = getReview();
        Review review = reviewService.save(reviewObject);
        Assert.assertNotNull(review);
        assertEquals(reviewService.getReviewByProductId(review.getProductId()).get(), review);

    }


    private Review getReview() {
        return new Review("test_product_id_1", 5.6, 50);
    }
}
