package com.example.java_group_11_homework_71_alfit_bashirov.repository;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByCustomer_Email(String email);
}
