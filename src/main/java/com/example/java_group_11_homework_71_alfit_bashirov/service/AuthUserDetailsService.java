package com.example.java_group_11_homework_71_alfit_bashirov.service;

import com.example.java_group_11_homework_71_alfit_bashirov.dto.CustomerDto;
import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.CustomerNotFoundException;
import com.example.java_group_11_homework_71_alfit_bashirov.exception.UserAlreadyRegisteredException;
import com.example.java_group_11_homework_71_alfit_bashirov.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;
    private final Map<String, String> localhHash = new HashMap<>();
    private final CustomerService customerService;
//    private final AuthenticationManager authenticationManager;

    public boolean checkUser(String email) {
        return customerRepository.existsByEmail(email);
    }

    public CustomerDto registration(CustomerDto customerDto) {
        if (checkUser(customerDto.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }
        Customer customer = Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .password(encoder.encode(customerDto.getPassword()))
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress())
                .build();
        customerRepository.save(customer);
        return CustomerDto.from(customer);
    }

    public String restorePassword(String email) {
        var customer = customerService.getByEmail(email);
        String hash = generateRandomLink(customer);
        customer.setPassword(encoder.encode(hash));
        customerRepository.save(customer);
        return hash;
    }

    private String generateRandomLink(Customer customer) {
        String uuid = UUID.randomUUID().toString();
        localhHash.put(customer.getEmail(), uuid);
        return uuid;
    }

    public void updatePassword(HttpServletRequest req, String hash) {
        Customer customer = getCustomerByHash(hash);
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(customer.getEmail(), hash);
//        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
    }

    private Customer getCustomerByHash(String hash) {
        for (Map.Entry<String, String> pair : localhHash.entrySet()) {
            if (pair.getValue().equals(hash)) {
                Customer customer = customerService.getByEmail(pair.getKey());
                localhHash.remove(customer.getEmail());
                return customer;
            }
            throw new CustomerNotFoundException();
        }
        return null;
    }


}

