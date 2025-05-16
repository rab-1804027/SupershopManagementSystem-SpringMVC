package com.bappi.supershopmanagementsystem.dto;

import com.bappi.supershopmanagementsystem.utils.Constants;

import com.bappi.supershopmanagementsystem.validation.DuplicateEmailValidator;
import com.bappi.supershopmanagementsystem.validation.PasswordMatchValidator;
import com.bappi.supershopmanagementsystem.validation.DuplicateUsernameValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordMatchValidator()
public class RegistrationRequestDto {
    @NotNull(message = Constants.ErrorMessage.NAME_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.NAME_EMPTY)
    private String name;

    @Email(message = Constants.ErrorMessage.INVALID_EMAIL)
    @NotNull(message = Constants.ErrorMessage.EMAIL_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.EMAIL_EMPTY)
    @DuplicateEmailValidator
    private String email;

    @NotNull(message = Constants.ErrorMessage.USERNAME_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.USERNAME_EMPTY)
    @DuplicateUsernameValidator
    private String username;

    @NotNull(message = Constants.ErrorMessage.PASSWORD_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.PASSWORD_EMPTY)
    private String password;

    @NotNull(message = Constants.ErrorMessage.ConfirmPassword_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.ConfirmPassword_EMPTY)
    private String confirmPassword;
}
