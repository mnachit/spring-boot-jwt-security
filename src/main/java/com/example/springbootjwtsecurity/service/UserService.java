package com.example.springbootjwtsecurity.service;

import com.example.springbootjwtsecurity.model.dto.LoginUserDto;
import com.example.springbootjwtsecurity.model.dto.RegisterUserDto;
import com.example.springbootjwtsecurity.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User saveUser(RegisterUserDto registerUserDto);
    public User login(LoginUserDto loginUserDto);
    public User findUserById(String email);
}
