package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.LoginRequestDto;
import com.bappi.supershopmanagementsystem.dto.UserDto;
import com.bappi.supershopmanagementsystem.dto.RegistrationRequestDto;
import com.bappi.supershopmanagementsystem.model.ProductCart;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.Constants;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/auth")
@SessionAttributes({"userId", "username", "role", "cart"})
@Slf4j
public class AuthController {

    private final UserService userService;
    private final ProductCart cart;

    @GetMapping("/register")
    public String registration() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationRequestDto") RegistrationRequestDto registrationRequestDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("inputErrors", result.getFieldErrors());
            return "registration";
        }

        userService.save(registrationRequestDto);
        log.info("User {} successfully registered", registrationRequestDto.getUsername());
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginRequestDto") LoginRequestDto loginRequestDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.error("Login Error: {}", result.getFieldErrors());
            model.addAttribute("errors", result.getFieldErrors());
            return "login";
        }

        UserDto userDto = userService.findUser(loginRequestDto);

        if (userDto != null) {
            model.addAttribute("userId", userDto.getId());
            model.addAttribute("username", loginRequestDto.getUsername());
            model.addAttribute("role", userDto.getRole());
            cart.initCart();
            model.addAttribute("cart", cart);
            log.info("User: {} logged in", loginRequestDto.getUsername());
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
