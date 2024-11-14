package com.example.springbootjwtsecurity.util;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class ErrorMessage {
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }
}
