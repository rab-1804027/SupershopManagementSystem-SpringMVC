package com.bappi.supershopmanagementsystem.dto;

import com.bappi.supershopmanagementsystem.utils.FormatTime;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserInfoDto {
    @Setter @Getter
    private String name;
    @Setter @Getter
    private String username;
    @Setter @Getter
    private String email;
    private String registrationTime;

    public UserInfoDto(String name, String username, String email, LocalDateTime registrationTime) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.registrationTime = FormatTime.formatTime(registrationTime);
    }


    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = FormatTime.formatTime(registrationTime);
    }

}
