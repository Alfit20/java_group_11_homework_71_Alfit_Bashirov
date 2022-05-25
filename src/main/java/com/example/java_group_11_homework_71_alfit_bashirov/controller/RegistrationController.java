package com.example.java_group_11_homework_71_alfit_bashirov.controller;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.CustomerDto;
import com.example.java_group_11_homework_71_alfit_bashirov.service.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final AuthUserDetailsService authUserDetailsService;

    @GetMapping
    public String getCustomerRegistration(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerDto());
        }
        return "registration";
    }


    @PostMapping
    public String customerRegistration(@Valid CustomerDto customerDto, BindingResult bindingResult,
                                       RedirectAttributes attributes) {
        if (authUserDetailsService.checkUser(customerDto.getEmail())) {
            attributes.addFlashAttribute("dto", customerDto);
            return "redirect:/registration";
        }
        if (bindingResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:/registration";
        }
        authUserDetailsService.registration(customerDto);
        return "redirect:/login";
    }

}
