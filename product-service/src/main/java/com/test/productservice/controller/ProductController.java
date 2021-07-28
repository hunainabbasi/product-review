package com.test.productservice.controller;

import com.test.productservice.model.Review;
import com.test.productservice.service.ProductService;
import com.test.productservice.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.IIOException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product/{product_Id}")
    public ResponseEntity getReviewByProductId(@PathVariable String product_Id) throws ExecutionException, InterruptedException {

        HashMap externalInfoProduct = new HashMap(0);

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            try {
                return productService.getProduct(product_Id);
            }  catch (IOException | EntityNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).thenAcceptAsync(result -> setProductValues(result, externalInfoProduct));
        future.get();
        if (externalInfoProduct.isEmpty()) {
            throw new EntityNotFoundException("No product found for id = " + product_Id);
        }
        Optional<Review> productReview = productService.getProductReview(product_Id);
        HashMap response = new HashMap();
        response.put("product", externalInfoProduct);
        response.put("review", productReview.isEmpty() ? "No Review Found" : productReview);

        return ResponseEntity.ok(response);
    }


    private void setProductValues(HashMap productResult, HashMap productMap) {
        productMap.clear();
        productMap.putAll(productResult);
    }

}
