package com.example.java_group_11_homework_71_alfit_bashirov.repository;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer_Email(String email);

    boolean existsByProductIdAndCustomerEmail(Long id, String email);
}
