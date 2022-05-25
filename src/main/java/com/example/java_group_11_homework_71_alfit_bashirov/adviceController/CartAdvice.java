package com.example.java_group_11_homework_71_alfit_bashirov.adviceController;

import com.example.java_group_11_homework_71_alfit_bashirov.helpers.Constants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CartAdvice {

    @ModelAttribute(Constants.CART_ID)
    public List<String> getCartModel(HttpSession session) {
        var list = session.getAttribute(Constants.CART_ID);
        if (list == null) {
            session.setAttribute(Constants.CART_ID, new ArrayList<>());
        }
        return (List<String>) session.getAttribute(Constants.CART_ID);
    }

}
