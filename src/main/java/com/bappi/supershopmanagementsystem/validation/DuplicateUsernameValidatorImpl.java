package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DuplicateUsernameValidatorImpl implements ConstraintValidator<DuplicateUsernameValidator, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        UserDto userDto = userService.findByUsername(username);
        boolean isValid = (userDto == null);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.Duplicate_Username)
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
