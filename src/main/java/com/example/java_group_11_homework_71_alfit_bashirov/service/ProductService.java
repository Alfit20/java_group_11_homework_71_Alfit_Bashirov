package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.ProductDto;
import com.example.java_group_11_homework_71_alfit_bashirov.dto.ReviewDto;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.ResourceNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.OrderRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.ProductRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;


    // Постраничное отображение списка товаров.
    public Page<ProductDto> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductDto::from);
    }

    public List<ProductDto> builder(List<Product> products) {
        return products.stream().map(e -> ProductDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .price(e.getPrice())
                        .image(e.getImage())
                        .description(e.getDescription())
                        .category(e.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    public Page<ProductDto> search(String type, String value, Pageable pageable) {
        if (type.equals("name")) {
            return productRepository.searchByName(value, pageable).map(ProductDto::from);
        }
        if (type.equals("price")) {
            return productRepository.findProductByPrice(Integer.parseInt(value), pageable).map(ProductDto::from);
        }
        if (type.equals("category")) {
            return productRepository.searchByCategory(value, pageable).map(ProductDto::from);
        }
        if (type.equals("description")) {
            return productRepository.searchByDescription(value, pageable).map(ProductDto::from);
        }
        return null;
    }

    public ProductDto getProductById(Long id) {
        return ProductDto.from(productRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public List<ReviewDto> getAllProductReviews(Long id) {
        return reviewRepository.findByProductId(id).stream().map(ReviewDto::from).collect(Collectors.toList());
    }

    public int countReviews(Long id) {
        return getAllProductReviews(id).size();
    }




}

