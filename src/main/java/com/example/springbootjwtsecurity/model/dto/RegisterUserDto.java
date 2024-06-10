package com.example.springbootjwtsecurity.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterUserDto {
    private String fullName;

    private String email;

    private String password;
}
