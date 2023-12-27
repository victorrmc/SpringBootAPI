package com.bitboxer2.SpringBootAPI.controller;

import com.bitboxer2.SpringBootAPI.dto.ProductDTO;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products/get")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/products/find/{id}")
    public Product findProduct(@PathVariable Long id){
        return productService.findProduct(id);
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return "The Product was created correctly";
    }

    @DeleteMapping("/products/delete/{id}")
    public String createProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "The Product was deleted correctly";
    }

    @PutMapping("/products/edit")
    public String editProduct(@RequestBody Product product) throws Exception {
        productService.editProduct(product);
        return "The Product was edited correctly";
    }

}
