package com.example.java_group_11_homework_71_alfit_bashirov.dto;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Order;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;

    @Positive
    private Integer price;

    @NotNull
    @JsonProperty("customer_id")
    private Customer customer;

    @NotNull
    @JsonProperty("product_id")
    private Product product;

    @NotNull
    @JsonProperty("order_date")
    private LocalDateTime orderDate;

    public static OrderDto from(Order order) {
        return builder()
                .id(order.getId())
                .price(order.getPrice())
                .customer(order.getCustomer())
                .product(order.getProduct())
                .orderDate(LocalDateTime.now())
                .build();

    }
}
