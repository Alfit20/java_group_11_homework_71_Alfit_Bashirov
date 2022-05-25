package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import com.example.java_group_11_homework_71_alfit_bashirov.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/order/quantity")
    public String updateQuantity(@RequestParam(value = "quantity") List<Integer> quantity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        orderService.updateQuantity(username, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/order")
    public String addToOrder() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        orderService.addOrder(username);
        return "redirect:/review";
    }
}
