package com.Ecommerce.Service;

import com.Ecommerce.Exception.ProductException;
import com.Ecommerce.Repository.RatingRepository;
import com.Ecommerce.Request.RatingRequest;
import com.Ecommerce.model.Product;
import com.Ecommerce.model.Rating;
import com.Ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();

        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.getAllProductRating(productId);
    }
}
