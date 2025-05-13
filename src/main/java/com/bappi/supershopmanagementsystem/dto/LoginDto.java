package com.bappi.supershopmanagementsystem.dto;

import com.bappi.supershopmanagementsystem.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LoginDto {
    @NotNull(message = Constants.ErrorMessage.USERNAME_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.USERNAME_EMPTY)
    private String username;

    @NotNull(message = Constants.ErrorMessage.PASSWORD_EMPTY)
    @NotEmpty(message = Constants.ErrorMessage.PASSWORD_EMPTY)
    private String password;
}
