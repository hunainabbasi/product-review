package com.test.productservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.productservice.model.Review;
import com.test.productservice.repository.ReviewRepository;
import com.test.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Value("${external.api.url}")
    private String EXTERNAL_API_URL;

    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    ReviewRepository reviewRepository;

    public HashMap getProduct(String productId) throws EntityNotFoundException, IOException {
        String uri = EXTERNAL_API_URL + productId;

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).version(HttpClient.Version.HTTP_2).GET()
                    .build();
            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(Charset.forName("UTF-8")));
            String responseBody = response.body();
            HashMap readValue = jacksonObjectMapper.readValue(responseBody, HashMap.class);
            return readValue;
        } catch (InterruptedException | URISyntaxException ie){
            ie.printStackTrace();
        }
        return null;
    }


    public Optional<Review> getProductReview(String product_id){
        return reviewRepository.findByProductId(product_id);
    }

}
