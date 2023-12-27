package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.dto.ProductDTO;

import java.util.*;


public interface IProductService {
    public void createProduct(Product product);

    public List<ProductDTO> getProducts();

    public void deleteProduct(Long id);
    public Product findProduct(Long id);
    public void editProduct(Product product) throws Exception;
    public void desactivateProduct(Long id, String reason) throws Exception;
}
