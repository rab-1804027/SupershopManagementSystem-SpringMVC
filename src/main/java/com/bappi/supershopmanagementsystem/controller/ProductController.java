package com.bappi.supershopmanagementsystem.controller;

import com.bappi.supershopmanagementsystem.dto.ProductDto;
import com.bappi.supershopmanagementsystem.dto.SaleDetailsDto;
import com.bappi.supershopmanagementsystem.dto.SaleDto;
import com.bappi.supershopmanagementsystem.model.*;
import com.bappi.supershopmanagementsystem.service.ProductService;
import com.bappi.supershopmanagementsystem.service.SaleDetailsService;
import com.bappi.supershopmanagementsystem.service.SaleService;
import com.bappi.supershopmanagementsystem.service.UserService;
import com.bappi.supershopmanagementsystem.utils.InvoicePdfGenerator;
import com.bappi.supershopmanagementsystem.validation.ProductValidator;
import com.bappi.supershopmanagementsystem.utils.FormatTime;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductValidator productValidator;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final SaleService saleService;
    private final UserService userService;
    private final SaleDetailsService saleDetailsService;

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

    @GetMapping("/cart")
    public String getCart(){
        return "productCart";
    }

    @GetMapping("/saleRecords")
    public String getSaleRecords(@SessionAttribute("userId") int userId, Model model){
        List<SaleDto> saleRecords = saleService.findAllByUserId(userId);
        model.addAttribute("saleRecords", saleRecords);
        return "saleRecords";
    }

    @RequestMapping(value = "/invoice", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<byte[]> getInvoice(@RequestParam("saleId") int saleId) throws Exception {

        Sale sale = saleService.findById(saleId);
        List<SaleDetailsDto> saleDetails = saleDetailsService.findAllBySale(sale);

        byte[] pdfBytes = InvoicePdfGenerator.generateInvoicePdf(
                saleDetails,
                sale.getTotalPrice(),
                FormatTime.formatTime(sale.getSaleTime())
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Invoice_" + saleId + ".pdf");
        log.info("Invoice of saleId:{} successfully generated", saleId);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("productDto") ProductDto productDto, @SessionAttribute("userId") int userId, Model model){
         Map<String, String> errors = productValidator.validate(productDto.getName(), productDto.getPrice(), productDto.getStockQuantity());
         if(!errors.isEmpty()){
             model.addAttribute("errors", errors);
             return "productForm";
         }
         productService.save(userId, productDto);
         log.info("Product {} successfully added", productDto.getName());
         return "redirect:/api/v1/dashboard";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("productDto") ProductDto productDto, @RequestParam("id") int id, Model model){
        Product product = modelMapper.map(productDto, Product.class);
        product.setId(id);
        productService.update(product);
        log.info("Product {} successfully updated", product.getName());
        return "redirect:/api/v1/dashboard";
    }

    @PostMapping("/cart/add")
    public String addToCart(@SessionAttribute("cart") ProductCart cart, @RequestParam("id") int id,
                            @RequestParam("quantity") int quantity, Model model){

        Product product = productService.findById(id);
        String error = productValidator.validateCartItemQuantity(quantity, product.getStockQuantity());

        if(error != null)
        {
            log.info("Quantity of {} is not valid. Error: {}", product.getName(), error);
            model.addAttribute("error", error);
            return "forward:/api/v1/dashboard";
        }

        CartItem cartItem = new CartItem(product, quantity);
        cart.add(cartItem);
        model.addAttribute("cart", cart);

        return "redirect:/api/v1/dashboard";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@SessionAttribute("cart") ProductCart cart, @RequestParam("id") int id, Model model){
        cart.removeById(id);
        model.addAttribute("cart", cart);
        return "productCart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(@SessionAttribute("cart") ProductCart cart, @SessionAttribute("userId") int userId, Model model){

        User user = userService.findById(userId);
        double totalPrice = cart.getTotalPrice();

        Sale sale = new Sale(user, totalPrice);
        sale = saleService.save(sale);

        saleDetailsService.save(sale, cart);
        cart.clearCart();
        model.addAttribute("cart", cart);
        log.info("Sale {} successfully completed", sale.getId());

        return "forward:/api/v1/product/invoice?saleId=" + sale.getId();
    }

}
