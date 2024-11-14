package com.example.springbootjwtsecurity.service.impl;

import com.example.springbootjwtsecurity.exception.ValidationException;
import com.example.springbootjwtsecurity.model.dto.LoginUserDto;
import com.example.springbootjwtsecurity.model.dto.RegisterUserDto;
import com.example.springbootjwtsecurity.model.entity.User;
import com.example.springbootjwtsecurity.repository.UserRepository;
import com.example.springbootjwtsecurity.service.UserService;
import com.example.springbootjwtsecurity.util.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    @Override
    public User saveUser(RegisterUserDto registerUserDto) {
        User user = new User();
        List<ErrorMessage> errorMessages = new ArrayList<>();

        if (userRepository.findByEmail(registerUserDto.getEmail()).isPresent()){
            errorMessages.add(new ErrorMessage("Email already exists"));
        }

        if (errorMessages.size() > 0){
            throw new ValidationException(errorMessages);
        }

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
