package com.bappi.supershopmanagementsystem.mapper;

import com.bappi.supershopmanagementsystem.dto.UserInfoDto;
import com.bappi.supershopmanagementsystem.dto.RegistrationRequestDto;
import com.bappi.supershopmanagementsystem.model.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
public class UserMapper {

    public User toEntity(RegistrationRequestDto userRegistrationDto) {
        String name = userRegistrationDto.getName();
        String email = userRegistrationDto.getEmail();
        String username = userRegistrationDto.getUsername();
        String password = userRegistrationDto.getPassword();
        return User.builder()
                .name(name)
                .email(email)
                .username(username)
                .password(password).build();
    }

    public UserInfoDto toInfoDto(User user){
        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();
        LocalDateTime registrationTime = user.getRegistrationTime();
        return new UserInfoDto(name, username, email, registrationTime);
    }
}
