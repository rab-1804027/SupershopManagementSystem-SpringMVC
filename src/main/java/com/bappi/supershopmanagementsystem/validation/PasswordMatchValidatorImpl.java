package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.dto.RegistrationRequestDto;
import com.bappi.supershopmanagementsystem.utils.Constants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordMatchValidatorImpl implements ConstraintValidator<PasswordMatchValidator, RegistrationRequestDto> {

    @Override
    public boolean isValid(RegistrationRequestDto userRegistrationDto, ConstraintValidatorContext context) {
        boolean isValid = userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(Constants.ErrorMessage.PASSWORD_NOT_MATCH)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return isValid;
    }

}
