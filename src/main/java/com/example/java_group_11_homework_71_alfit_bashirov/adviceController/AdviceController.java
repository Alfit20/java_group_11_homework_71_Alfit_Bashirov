package com.example.java_group_11_homework_71_alfit_bashirov.adviceController;

import com.example.java_group_11_homework_71_alfit_bashirov.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(annotations = Controller.class)
public class AdviceController {


    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleRNF(Model model, ResourceNotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "notFound";
    }

    @ExceptionHandler(Exception.class)
    public String handle(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "notFound";
    }


}

