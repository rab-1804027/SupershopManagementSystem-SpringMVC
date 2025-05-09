package com.bappi.supershopmanagementsystem.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String username;
    private String password;
    private String role;

    public UserDto(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

