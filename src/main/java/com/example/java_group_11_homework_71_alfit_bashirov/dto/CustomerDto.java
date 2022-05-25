package com.example.java_group_11_homework_71_alfit_bashirov.dto;

import com.example.java_group_11_homework_71_alfit_bashirov.entity.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Size(min = 3, max = 128, message = "Name should be between 3 and 128 characters")
    private String email;

    @NotBlank
    private String password;

    @Pattern(regexp = "^\\d+$")
    @NotBlank(message = "Phone number should not be empty")
    @Size(min = 1, max = 128, message = "Phone number should be between 2 and 50 characters")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "Address number should not be empty")
    @Size(min = 3, max = 128, message = "Address should be between 3 and 128 characters")
    private String address;

    public static CustomerDto from(Customer customer) {
        return builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}

