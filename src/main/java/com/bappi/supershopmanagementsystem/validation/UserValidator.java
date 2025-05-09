package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.UserDto;
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

    public Map<String, String> validateRegistration(User user, String confirmPassword) {
        Map<String, String> errors = new HashMap();
        String name = user.getName();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        if(StringUtils.isNullorEmpty(name)) {
            errors.put("name",Constants.ErrorMessage.NAME_EMPTY);
        }
        if(!email.matches(Constants.EMAIL_REGEX)) {
            errors.put("email",Constants.ErrorMessage.INVALID_EMAIL);
        }
        if(StringUtils.isNullorEmpty(username)) {
            errors.put("username",Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(StringUtils.isNullorEmpty(password)) {
            errors.put("password",Constants.ErrorMessage.PASSWORD_EMPTY);
        }

        if(!password.equals(confirmPassword)) {
            errors.put("passwordMismatch",Constants.ErrorMessage.PASSWORD_NOT_MATCH);
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
    public Map<String,String> validateLogin(String username, String password) {

        Map<String,String> errors = new HashMap<>();

        if(StringUtils.isNullorEmpty(username)) {
            errors.put("username",Constants.ErrorMessage.USERNAME_EMPTY);
        }
        if(StringUtils.isNullorEmpty(password)) {
            errors.put("password",Constants.ErrorMessage.PASSWORD_EMPTY);
        }
        return errors;
    }
}
