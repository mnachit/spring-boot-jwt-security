package com.example.springbootjwtsecurity.service.impl;

import com.example.springbootjwtsecurity.model.dto.LoginUserDto;
import com.example.springbootjwtsecurity.model.dto.RegisterUserDto;
import com.example.springbootjwtsecurity.model.entity.User;
import com.example.springbootjwtsecurity.repository.UserRepository;
import com.example.springbootjwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    @Override
    public User saveUser(RegisterUserDto registerUserDto) {
        User user = new User();
        user.setEmail(registerUserDto.getEmail());
        user.setFullName(registerUserDto.getFullName());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(null);
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User login(LoginUserDto loginUserDto) {
        if (userRepository.findByEmail(loginUserDto.getEmail()).isPresent() &&
                passwordEncoder.matches(loginUserDto.getPassword(), userRepository.findByEmail(loginUserDto.getEmail()).get().getPassword())){
            return userRepository.findByEmail(loginUserDto.getEmail()).get();
        }
        return null;
    }

    @Override
    public User findUserById(String email) {
        if (userRepository.findByEmail(email).isPresent())
            return userRepository.findByEmail(email).get();
        else
            return null;
    }
}
