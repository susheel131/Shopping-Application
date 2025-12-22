package com.ecommerce.userServices.dto;

import lombok.Data;

import java.util.List;

import com.ecommerce.userServices.entity.Role;

@Data
public class UserProfileDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private List<AddressDTO> addresses;
}
