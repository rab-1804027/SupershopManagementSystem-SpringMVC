package com.bappi.supershopmanagementsystem.model;

import com.bappi.supershopmanagementsystem.dto.ProductDto;
import com.bappi.supershopmanagementsystem.service.ProductService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Component
@RequiredArgsConstructor
public class ProductCart {

    private final ProductService productService;

    private List<CartItem> cartItems;
    private double totalPrice;

    public void initCart() {
        cartItems = new ArrayList<>();
        totalPrice = 0;
    }

    public void clearCart() {
        cartItems.clear();
        totalPrice = 0;
    }

    public void add(CartItem cartItem) {
        for (CartItem item : cartItems) {
            if (Objects.equals(item.getProduct().getId(), cartItem.getProduct().getId())) {
                totalPrice = totalPrice - item.getPrice();
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                totalPrice = totalPrice + item.getPrice();

                Product product = item.getProduct();
                product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
                productService.update(product);
                return;
            }
        }

        cartItems.add(cartItem);
        totalPrice = totalPrice + cartItem.getPrice();

        Product product = cartItem.getProduct();
        product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
        productService.update(product);
    }

    public void removeById(int id){
        for(CartItem item : cartItems){
            if(Objects.equals(item.getProduct().getId(), id))
            {
                cartItems.remove(item);
                totalPrice = totalPrice - item.getPrice();

                Product product = item.getProduct();
                product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
                productService.update(product);
                return;
            }
        }
    }

}
