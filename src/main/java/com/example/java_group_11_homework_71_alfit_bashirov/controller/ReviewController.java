package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.ReviewDto;
import com.example.java_group_11_homework_71_alfit_bashirov.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    public String reviewOrder(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        model.addAttribute("products", reviewService.getCartProducts(username));
        return "order";
    }

    // Чтобы увидеть отзывы нажмите на текст "Отзывы"
    @PostMapping("/review/{id}")
    public String addReviewToProduct(@PathVariable Long id, ReviewDto reviewDto, RedirectAttributes attributes) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        if (!reviewService.checkingCustomerOrder(id, username)) {
            attributes.addFlashAttribute("dto", reviewDto);
            return "redirect:/" + id;
        }
        reviewService.addReview(id, reviewDto, username);
        return "redirect:/" + id;
    }


}

