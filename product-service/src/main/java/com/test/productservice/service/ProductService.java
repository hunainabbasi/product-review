package com.test.productservice.service;

import com.test.productservice.model.Review;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public interface ProductService {

    public HashMap getProduct(String productId) throws EntityNotFoundException , IOException;
    public Optional<Review> getProductReview(String product_id);

}
