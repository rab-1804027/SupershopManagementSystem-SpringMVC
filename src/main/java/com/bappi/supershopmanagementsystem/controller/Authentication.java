package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.UserRegistrationDto;
import com.bappi.supershopmanagementsystem.mapper.UserMapper;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import com.bappi.supershopmanagementsystem.utils.PasswordHashing;
import com.bappi.supershopmanagementsystem.validation.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth/v1")
@SessionAttributes({"userId", "username","role"})
public class Authentication {

    private final UserService userService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(Authentication.class);

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        User user = userMapper.toEntity(userRegistrationDto);
        Map<String, String> errors = userValidator.validateRegistration(user, confirmPassword);
        if(!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "registration";
        }

        logger.info("User {} successfully registered", user.getUsername());
        userService.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Map<String, String> errors = userValidator.validateLogin(username, password);
        if(!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "login";
        }

        UserDto userDto = userService.findByUsername(username);

        if(userDto != null && PasswordHashing.checkPassword(password, userDto.getPassword())) {
            model.addAttribute("userId", userDto.getId());
            model.addAttribute("username", username);
            model.addAttribute("role", userDto.getRole());

            return "redirect:/api/v1/dashboard";
        }
        else{
            model.addAttribute("error", Constants.ErrorMessage.LOGIN);
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/auth/v1/login";
    }
}
