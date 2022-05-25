package com.example.java_group_11_homework_71_alfit_bashirov.dto;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Cart;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;

    private Integer price;

    @JsonProperty("product_id")
    private Product product;

    @Positive
    private Integer quantity;

    @JsonProperty("customer_id")
    private Customer customer;

    public static CartDto from(Cart cart) {
        return builder()
                .id(cart.getId())
                .price(cart.getPrice())
                .product(cart.getProduct())
                .quantity(cart.getQuantity())
                .customer(cart.getCustomer())
                .build();

    }
}

