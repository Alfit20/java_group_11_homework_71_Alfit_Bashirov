package com.example.java_group_11_homework_71_alfit_bashirov.repository;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long id);
}
