package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.UserInfoDto;
import com.bappi.supershopmanagementsystem.enums.ApprovalStatus;
import com.bappi.supershopmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@SessionAttributes("cart")
@Slf4j
public class DashboardController {

    private final UserService userService;

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET, RequestMethod.POST})
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
                model.addAttribute("error", "Your registration is not approved yet. Please contact the admin for more details. Thank you for your patience.");
                return "login";
            }
        }
    }

    @GetMapping("/approveShopkeeper")
    public String approveShopkeeper(@RequestParam("username") String username){

        String role = "Shopkeeper";
        userService.assignRole(username, role, ApprovalStatus.APPROVED);
        log.info("Admin approved Shopkeeper: {} ", username);
        return "redirect:/api/v1/dashboard";
    }

    @GetMapping("/rejectShopkeeper")
    public String rejectShopkeeper(@RequestParam("username") String username){

        String role = "null";
        userService.assignRole(username, role, ApprovalStatus.REJECTED);
        log.info("Admin rejected Shopkeeper: {} ", username);
        return "redirect:/api/v1/dashboard";
    }

}
