package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.LoginDto;
import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.UserRegistrationDto;
import com.bappi.supershopmanagementsystem.mapper.UserMapper;
import com.bappi.supershopmanagementsystem.model.ProductCart;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import com.bappi.supershopmanagementsystem.utils.PasswordHashing;
import com.bappi.supershopmanagementsystem.validation.UserValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/auth")
@SessionAttributes({"userId", "username", "role", "cart"})
@Slf4j
public class AuthController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final ProductCart cart;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("inputErrors", result.getFieldErrors());
            return "registration";
        }

        Map<String, String> errors = userValidator.validateRegistration(userRegistrationDto);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "registration";
        }

        User user = userMapper.toEntity(userRegistrationDto);
        log.info("User {} successfully registered", user.getUsername());
        userService.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser( @Valid @ModelAttribute("loginDto") LoginDto loginDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("Login Error: {}", result.getFieldErrors());
            model.addAttribute("errors", result.getFieldErrors());
            return "login";
        }

        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        UserDto userDto = userService.findByUsername(username);

        if (userDto != null && PasswordHashing.checkPassword(password, userDto.getPassword())) {
            model.addAttribute("userId", userDto.getId());
            model.addAttribute("username", username);
            model.addAttribute("role", userDto.getRole());
            cart.initCart();
            model.addAttribute("cart", cart);
            log.info("User: {} logged in", username);
            return "redirect:/api/v1/dashboard";
        } else {
            model.addAttribute("error", Constants.ErrorMessage.LOGIN);
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        log.info("User {} logged out", session.getAttribute("username"));
        session.invalidate();
        return "redirect:/api/v1/auth/login";
    }
}
