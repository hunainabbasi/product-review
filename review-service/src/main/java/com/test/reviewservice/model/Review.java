package com.test.reviewservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor

public class Review {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)

    private Double averageReviewScore;

    @Column(nullable = false)
    private Integer numberOfReviews;

    public Review(String productId, Double averageReviewScore, Integer numberOfReviews) {
        this.productId = productId;
        this.averageReviewScore = averageReviewScore;
        this.numberOfReviews = numberOfReviews;
    }
}
