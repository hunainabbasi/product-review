package com.test.reviewservice.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewDTO {

    @NotNull
    private String productId;

    @NotNull
    private Double averageReviewScore;

    @NotNull
    private Integer numberOfReviews;
}
