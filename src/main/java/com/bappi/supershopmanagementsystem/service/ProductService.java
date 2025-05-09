package com.bappi.supershopmanagementsystem.service;

import com.bappi.supershopmanagementsystem.dao.ProductDao;
import com.bappi.supershopmanagementsystem.dto.ProductDto;
import com.bappi.supershopmanagementsystem.model.Product;
import com.bappi.supershopmanagementsystem.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public void save(int userId, ProductDto productDto){
        User user = userService.findById(userId);
        Product product = new Product(user, productDto.getName(), productDto.getPrice(), productDto.getStockQuantity());
        productDao.save(product);
    }

    public Product findById(int id){
        return productDao.findById(id);
    }

    public void update(Product product){
        productDao.update(product);
    }

    public List findByUserId(int userId){
        return productDao.findByUserId(userId);
    }

}
