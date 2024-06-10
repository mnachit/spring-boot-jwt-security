package com.example.springbootjwtsecurity.controller;

import com.example.springbootjwtsecurity.config.JwtUtil;
import com.example.springbootjwtsecurity.model.dto.LoginUserDto;
import com.example.springbootjwtsecurity.model.dto.RegisterUserDto;
import com.example.springbootjwtsecurity.model.entity.User;
import com.example.springbootjwtsecurity.service.UserService;
import com.example.springbootjwtsecurity.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {
    private UserService userService;
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {
        Response<String> userDtoResponseResponse = new Response<>();
        User user = userService.saveUser(registerUserDto);
        String token = jwtUtil.createToken(user);
        userDtoResponseResponse.setMessage("User has been added");
        userDtoResponseResponse.setResult(token);
        return ResponseEntity.ok(userDtoResponseResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Response<String> userDtoResponseResponse = new Response<>();
        User authenticatedUser = userService.login(loginUserDto);
        String jwtToken = jwtUtil.createToken(authenticatedUser);
        userDtoResponseResponse.setResult(jwtToken);
//        userDtoResponseResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(userDtoResponseResponse);
    }
}
