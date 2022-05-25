package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.CustomerNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getByEmail(String email) {
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Не нашел такого пользователя"));
        return customer;
    }


}
