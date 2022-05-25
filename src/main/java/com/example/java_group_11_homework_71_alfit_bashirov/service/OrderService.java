package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Cart;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Order;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CartRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public void updateQuantity(String email, List<Integer> quantity) {
        var cart = cartRepository.findByCustomer_Email(email);
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(quantity.get(i));
            cartRepository.saveAll(cart);
        }
    }

    public void addOrder(String email) {
        List<Cart> cart = cartRepository.findByCustomer_Email(email);
        for (var c : cart) {
            orderRepository.save(Order.builder()
                    .price(c.getPrice())
                    .customer(c.getCustomer())
                    .product(c.getProduct())
                    .quantity(c.getQuantity())
                    .orderDate(LocalDateTime.now())
                    .build());
        }
        cartRepository.deleteAll(cart);
    }
}
