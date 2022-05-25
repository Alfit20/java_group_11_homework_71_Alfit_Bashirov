package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.OrderDto;
import com.example.java_group_11_homework_71_alfit_bashirov.dto.ReviewDto;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Review;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.CustomerNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.UserAlreadyRegisteredException;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CustomerRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.OrderRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.ProductRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    public List<OrderDto> getCartProducts(String email) {
        return orderRepository.findByCustomer_Email(email).stream()
                .map(OrderDto::from).collect(toList());
    }

    public void addReview(Long id, ReviewDto reviewDto, String email) {
        Customer author = customerRepository.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        Product product = productRepository.findById(id).get();
        if (!checkingCustomerOrder(id, author.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }
        reviewRepository.save(Review.builder()
                .text(reviewDto.getText())
                .dateAdded(LocalDateTime.now())
                .author(author)
                .product(product)
                .build());
    }

    public boolean checkingCustomerOrder(Long id, String email) {
        return orderRepository.existsByProductIdAndCustomerEmail(id, email);
    }
}
