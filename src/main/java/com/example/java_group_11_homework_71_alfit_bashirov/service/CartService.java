package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.CartDto;
import com.example.java_group_11_homework_71_alfit_bashirov.dto.ProductDto;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Cart;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.CustomerNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.ResourceNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.helpers.Constants;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CartRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CustomerRepository;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public void addItemToCart(Long id, String email) {
        var product = productRepository.findById(id).orElseThrow();
        var customerEmail = customerRepository.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        for (int i = 0; i < getCartProducts(email).size(); i++) {
            if(getCartProducts(email).get(i).getProduct().getName().equals(product.getName())) {
                throw new ResourceNotFoundException("Такой товар уже есть");
            }
        }
        cartRepository.save(Cart.builder()
                .price(product.getPrice())
                .product(product)
                .customer(customerEmail)
                .build());
    }

    public List<CartDto> getCartProducts(String email) {
        return cartRepository.findByCustomer_Email(email).stream()
                .map(CartDto::from).collect(Collectors.toList());
    }

    public int calculateSum(String email) {
        int sum = 0;
        for (int i = 0; i < getCartProducts(email).size(); i++) {
            sum += getCartProducts(email).get(i).getPrice() * getCartProducts(email).get(i).getQuantity();
        }
        return sum;
    }


    public void deleteProductInCart(Long id) {
        var product = cartRepository.findById(id).orElseThrow();
        cartRepository.delete(product);

    }

    public List<ProductDto> addItemToSession(Long id, HttpSession session) {
        try {
            var product = productRepository.findById(id).orElseThrow();
            if (session != null) {
                Object attr = session.getAttribute(Constants.CART_ID);
                if (attr == null) {
                    session.setAttribute(Constants.CART_ID, new ArrayList<String>());
                }
                var productDto = (List<ProductDto>) session.getAttribute(Constants.CART_ID);
                productDto.add(ProductDto.from(product));
                return productDto;
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Optional<CartDto> findById(Long id) {
        return cartRepository.findById(id).map(CartDto::from);
    }
}
