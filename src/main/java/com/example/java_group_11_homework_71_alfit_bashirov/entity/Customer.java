package com.example.java_group_11_homework_71_alfit_bashirov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Size(min = 3, max = 128, message = "Name should be between 3 and 128 characters")
    private String email;

    @NotBlank
    @Size(min = 8, max = 128)
    @Column(length = 128)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";

    @Pattern(regexp = "^\\d+$")
    @NotBlank(message = "Phone number should not be empty")
    @Size(min = 4, max = 128, message = "Phone number should be between 2 and 50 characters")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Address number should not be empty")
    @Size(min = 3, max = 128, message = "Address should be between 3 and 128 characters")
    private String address;

}
