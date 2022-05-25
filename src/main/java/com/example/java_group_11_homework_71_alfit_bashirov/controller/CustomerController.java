package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import com.example.java_group_11_homework_71_alfit_bashirov.exception.CustomerNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.service.AuthUserDetailsService;
import com.example.java_group_11_homework_71_alfit_bashirov.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AuthUserDetailsService service;

    // По этому методу и авторизация проходит если все верно введете. Проверка зайдите в профайл
    // в профайл может зайти тот кто залогиниться
    @GetMapping("/login")
    public String login(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        var user = customerService.getByEmail(username);
        model.addAttribute("dto", user);
        return "profile";
    }

    // Ошибка если порт занят
    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindExceptionResponseEntity(BindException ex) {
        var apiFieldErrors = ex.getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }


    @GetMapping("/restore-password")
    public String getIndex() {
        return "restore_password";
    }

    @PostMapping("/restore-password")
    public String getNewPair(@RequestParam String email, Model model) {
        String newPassword = service.restorePassword(email);
        if (!newPassword.isEmpty()) {
            model.addAttribute("dto", newPassword);
        }
        return "restore_password";
    }

    @GetMapping("/restore-password/{hash}")
    public String showFormNewPassword(@PathVariable String hash, HttpServletRequest req, Model model) {
        service.updatePassword(req, hash);
        model.addAttribute("newPassword", hash);
        return "new_password";
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException() {
        return new ResponseEntity<>("Пользователя с таким емеилом не существует", HttpStatus.OK);
    }


}
