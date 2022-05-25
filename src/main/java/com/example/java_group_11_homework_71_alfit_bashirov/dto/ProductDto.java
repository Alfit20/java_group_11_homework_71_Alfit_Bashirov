package com.example.java_group_11_homework_71_alfit_bashirov.dto;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Category;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
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
    @JsonProperty("category_id")
    private Category category;

    public static ProductDto from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .image(product.getImage())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();

    }
}
