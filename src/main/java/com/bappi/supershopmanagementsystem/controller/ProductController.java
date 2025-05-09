package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.ProductDto;
import com.bappi.supershopmanagementsystem.model.Product;
import com.bappi.supershopmanagementsystem.service.ProductService;
import com.bappi.supershopmanagementsystem.validation.ProductValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductValidator productValidator;
    private final ProductService productService;

    @GetMapping("/form")
    public String getProductForm(){
        return "productForm";
    }

    @GetMapping("/update")
    public String getUpdateForm(@RequestParam("id") int id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "productForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("productDto") ProductDto productDto, @SessionAttribute("userId") int userId, Model model){
         Map<String, String> errors = productValidator.validate(productDto.getName(), productDto.getPrice(), productDto.getStockQuantity());
         if(!errors.isEmpty()){
             model.addAttribute("errors", errors);
             return "productForm";
         }
         productService.save(userId, productDto);
         return "redirect:/api/v1/dashboard";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("productDto") ProductDto productDto, @RequestParam("id") int id, Model model){
        productService.updateById(productDto, id);
        return "redirect:/api/v1/dashboard";
    }

}
