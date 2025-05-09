package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.UserInfoDto;
import com.bappi.supershopmanagementsystem.enums.ApprovalStatus;
import com.bappi.supershopmanagementsystem.model.Product;
import com.bappi.supershopmanagementsystem.model.User;
import com.bappi.supershopmanagementsystem.service.ProductService;
import com.bappi.supershopmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/dashboard")
    public String getDashboard(@SessionAttribute("userId") int userId, @SessionAttribute("role") String role, Model model) {

        switch (role){
            case "Admin" -> {
                List<UserInfoDto> approvalShopkeers = userService.findByApprovalStatus(ApprovalStatus.APPROVED);
                List<UserInfoDto> pendingShopkeepers = userService.findByApprovalStatus(ApprovalStatus.PENDING);
                model.addAttribute("approvalShopkeeperList", approvalShopkeers);
                model.addAttribute("pendingShopkeeperList", pendingShopkeepers);
                return "adminDashboard";
            }
            case "Shopkeeper" -> {
                model.addAttribute("products", userService.findProductsByUserId(userId) );
                return "shopkeeperDashboard";
            }
            default -> {
                return "login";
            }

        }
    }

    @GetMapping("/approveShopkeeper")
    public String approveShopkeeper(@RequestParam("username") String username){

        String role = "Shopkeeper";
        userService.assignRole(username, role, ApprovalStatus.APPROVED);

        return "redirect:/api/v1/dashboard";
    }

    @GetMapping("/rejectShopkeeper")
    public String rejectShopkeeper(@RequestParam("username") String username){

        String role = "null";
        userService.assignRole(username, role, ApprovalStatus.REJECTED);

        return "redirect:/api/v1/dashboard";
    }

}
