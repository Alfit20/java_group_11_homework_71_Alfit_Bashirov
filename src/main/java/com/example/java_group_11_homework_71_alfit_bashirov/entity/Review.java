package com.example.java_group_11_homework_71_alfit_bashirov.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "review_table")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 128, message = "Text should be between 1 and 128 characters")
    private String text;

    @NotNull
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Customer author;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
