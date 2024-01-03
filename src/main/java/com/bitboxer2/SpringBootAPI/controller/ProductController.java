package com.bitboxer2.SpringBootAPI.controller;

import com.bitboxer2.SpringBootAPI.dto.ProductDTO;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin( origins = {"http://localhost:5173"})
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("get")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("find/{id}")
    public Product findProduct(@PathVariable Long id){
        return productService.findProduct(id);
    }

    @CrossOrigin(origins = {"http://localhost:5173"})
    @PostMapping("create")
    public String createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return "The Product was created correctly";
    }

    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "The Product was deleted correctly";
    }

    @PutMapping("edit")
    public String editProduct(@RequestBody Product product) throws Exception {
        productService.editProduct(product);
        return "The Product was edited correctly";
    }
    @PutMapping("desactivate/{id}")
    public String desactivateProduct(@PathVariable Long id, @RequestBody String reason) throws Exception {
        productService.desactivateProduct(id, reason);
        return "The Product was desactivated correctly";
    }

}
