package com.example.java_group_11_homework_71_alfit_bashirov.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "cart")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    private Integer price;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 128)
    @Builder.Default
    private Integer quantity = 1;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
