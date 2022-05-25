package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String productList(Model model, Pageable pageable, HttpServletRequest uriBuilder, HttpSession session) {
        var map = new HashMap<String, Object>();
        map.put("Идентификатор сессии", session.getId());
        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(key -> map.put(key, session.getAttribute(key).toString()));

        model.addAttribute("sessionAttributes", map);
        var products = productService.getProducts(pageable);
        String uri = uriBuilder.getRequestURI();
        PageUtil.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);
        return "products";
    }

    @GetMapping("{id}")
    public String productById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("reviews", productService.getAllProductReviews(id));
        model.addAttribute("count", productService.countReviews(id));
        return "product";
    }


    @GetMapping("/search")
    public String search(@RequestParam(value = "searchBy", required = false) String type,
                         @RequestParam(value = "search", required = false) String value,
                         Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        if (value == null || productService.search(type, value, pageable).getContent().isEmpty()) {
            throw new ResourceNotFoundException(value);
        }
        Page<ProductDto> products = productService.search(type, value, pageable);
        String uri = uriBuilder.getRequestURI();
        PageUtil.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri + "?" + "searchBy=" + type +
                "&" + "search=" + value);

        return "productBySearch";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleNotFoundException() {
        return new ResponseEntity<>("Неверный формат", HttpStatus.OK);
    }


}
