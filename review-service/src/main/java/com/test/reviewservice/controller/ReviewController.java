package com.test.reviewservice.controller;

import com.test.reviewservice.Exception.NotFoundException;
import com.test.reviewservice.dto.ReviewDTO;
import com.test.reviewservice.model.Review;
import com.test.reviewservice.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping("/{product_Id}")
    @PreAuthorize("permitAll")
    public Review getReviewByProductId(@PathVariable String product_Id) {
        return reviewService.getReviewByProductId(product_Id).orElseThrow(() -> new NotFoundException("Product: " + product_Id + " not found"));
    }

    @PostMapping()
    public ResponseEntity<Review> save(@RequestBody ReviewDTO reviewDTO) {
        ResponseEntity<Review> response = null;
        Review review = new Review();
        try {
            review = reviewService.save(convertToReview(reviewDTO));
            response = new ResponseEntity<>(review, HttpStatus.OK);
            return response;
        } catch (NotFoundException nfe) {
            logger.error("Resource not found:", nfe.getMessage());
            response = new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Exception occurs while saving review :", e.getMessage());
            response = new ResponseEntity<>(review, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> delete(@PathVariable("product_id") String product_id) {
        try {
            reviewService.delete(product_id);
            return ResponseEntity.ok("Entity deleted");
        } catch (NotFoundException nfe) {
            logger.error("Resource not found:", nfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Resource not found:", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    private ReviewDTO convertToDto(Review review) {
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }

    private Review convertToReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        return review;
    }


}
