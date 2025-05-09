package com.bappi.supershopmanagementsystem.model;

import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private int quantity;
    private double price;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }

}
