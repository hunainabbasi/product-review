package com.test.productservice.model;

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

    @Column
    @NotNull
    private String productId;

    @Column
    @NotNull
    private Double averageReviewScore;

    @Column
    @NotNull
    private Integer numberOfReviews;
}
