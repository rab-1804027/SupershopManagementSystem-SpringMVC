package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DuplicateEmailValidatorImpl implements ConstraintValidator<DuplicateEmailValidator, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        UserDto userDto = userService.findByEmail(email);
        boolean isValid = (userDto == null);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.Duplicate_Email)
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
