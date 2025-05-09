package com.bappi.supershopmanagementsystem.validation;

import com.bappi.supershopmanagementsystem.utils.NumberUtils;
import com.bappi.supershopmanagementsystem.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductValidator {
    public Map<String, String> validate(String name, Double price, int stockQuantity) {
        Map<String, String> errors = new HashMap<>();
        if(StringUtils.isNullorEmpty(name)) {
            errors.put("name", "Name cannot be empty or null");
        }
        if(NumberUtils.isNullOrInvalid(price)) {
            errors.put("price", "Price should be a positive number");
        }
        if(NumberUtils.isNullOrNegative(stockQuantity)) {
            errors.put("stockQuantity", "Stock quantity cannot be null or negative");
        }
        return errors;
    }
}
