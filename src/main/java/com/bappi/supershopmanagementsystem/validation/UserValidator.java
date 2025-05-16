package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.RegistrationRequestDto;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserService userService;

    public Map<String, String> validateRegistration(RegistrationRequestDto userRegistrationDto) {
        Map<String, String> errors = new HashMap<>();
        String username = userRegistrationDto.getUsername();
        String email = userRegistrationDto.getEmail();

        return errors;
    }
}
