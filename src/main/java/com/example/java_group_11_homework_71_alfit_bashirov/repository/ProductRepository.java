package com.example.java_group_11_homework_71_alfit_bashirov.repository;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

//    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
//    List<Product> searchByName(String name);
//
//    List<Product> findProductByPrice(Integer price);
//
//    @Query("SELECT p FROM Product p WHERE p.category.categoryName LIKE %?1%")
//    List<Product> searchByCategory(String category);
//
//    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1%")
//    List<Product> searchByDescription(String description);


    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Page<Product> searchByName(String name, Pageable pageable);

    Page<Product> findProductByPrice(Integer price, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.categoryName LIKE %?1%")
    Page<Product> searchByCategory(String category, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.description LIKE %?1%")
    Page<Product> searchByDescription(String description, Pageable pageable);
}
