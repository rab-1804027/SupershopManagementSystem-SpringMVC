package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.UserRegistrationDto;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import com.bappi.supershopmanagementsystem.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserService userService;

    public Map<String, String> validateRegistration(UserRegistrationDto userRegistrationDto) {
        Map<String, String> errors = new HashMap<>();
        String username = userRegistrationDto.getUsername();
        String email = userRegistrationDto.getEmail();
        String password = userRegistrationDto.getPassword();
        String confirmPassword = userRegistrationDto.getConfirmPassword();

        if(!password.equals(confirmPassword)) {
            errors .put("passwordMismatch",Constants.ErrorMessage.PASSWORD_NOT_MATCH);
        }

        UserDto userDto = userService.findByUsername(username);
        if (userDto!=null) {
            errors.put("username",Constants.ErrorMessage.USER_EXISTS);
        }
        userDto = userService.findByEmail(email);
        if (userDto!=null) {
            errors.put("email",Constants.ErrorMessage.EMAIL_EXISTS);
        }
        return errors;
    }
}
