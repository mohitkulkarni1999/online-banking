package com.bank.authentication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponseDto {

    private String jwtToken;
    private String userName;
    private List<String> authorities;

    public LoginResponseDto(String userName, List<String> authorities, String jwtToken) {
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.authorities = authorities;
    }
}
