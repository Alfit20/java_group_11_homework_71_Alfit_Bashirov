package com.example.java_group_11_homework_71_alfit_bashirov.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "product_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Min(2)
    private String name;

    @Positive
    private Integer price;

    @NotBlank
    @Size(min = 1, max = 128)
    private String image;

    @NotBlank
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}

