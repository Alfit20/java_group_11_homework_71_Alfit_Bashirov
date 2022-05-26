package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.CartDto;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.ErrorResponse;
import com.example.java_group_11_homework_71_alfit_bashirov.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpSession;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public String cart(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        model.addAttribute("cartItems", cartService.getCartProducts(username));
        model.addAttribute("sum", cartService.calculateSum(username));
        return "cart";
    }


//    @PostMapping("{id}")
//    @ResponseBody
//    public ResponseEntity<Void> addToCart(@PathVariable Long id, HttpSession session, @RequestBody String json) throws JsonProcessingException {
////        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        String username = ((UserDetails) principal).getUsername();
////        cartService.addItemToCart(id, username, quantity);
////        cartService.addItemToSession(id, session);
//        var x = new ObjectMapper().readValue(json, CartDto.class);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("{id}")
    public String addToCart(@RequestParam(value = "quantity") Integer quantity, @PathVariable Long id, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        cartService.addItemToCart(id, username, quantity);
        cartService.addItemToSession(id, session);
        return "redirect:/cart";
    }

//    @PostMapping("{id}")
//    @ResponseBody
//    public boolean addToCart(@RequestParam(value = "quantity") Integer quantity, @PathVariable Long id, HttpSession session) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//        cartService.addItemToCart(id, username, quantity);
//        cartService.addItemToSession(id, session);
//        return true;
//    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        cartService.deleteProductInCart(id);
        return "redirect:/cart";
    }

    // Если пользователь не авторизован
    @ExceptionHandler(AuthException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handleNotFoundException(AuthException authException) {
        return ErrorResponse.builder()
                .message(authException.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .timestamp(now())
                .build();
    }
}