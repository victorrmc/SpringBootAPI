package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.dto.ProductDTO;
import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.model.Supplier;
import com.bitboxer2.SpringBootAPI.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository repoProduct;
    @Override
    public void createProduct(Product product) {
        if(product.getState() == null){
            product.setState(Product.ProductState.ACTIVE);
        }
        product.setCreationDate(new Date());
        repoProduct.save(product);
    }
    @Override
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        productList = repoProduct.findAll();
        for(Product product : productList){
            ProductDTO productDTO = new ProductDTO(product);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public void editProduct(Product product) throws Exception {
        Product productBD = this.findProduct(product.getProductId());
        Set<Long> uniqueIds = new HashSet<>();
        if(productBD.getState() == Product.ProductState.ACTIVE){
            for (Supplier supplier : product.getSupplierList()){
                Long productId = supplier.getSupplierId();
                if (!uniqueIds.add(productId)) {
                    throw new Exception("The product already has a Supplier associated with this ID." + productId);
                }
            }
            repoProduct.save(product);
        }else{
            throw new Exception("The product is DISCONTINUED and can't be edited.");
        }

    }

    @Override
    public void desactivateProduct(Long id, String reason) throws Exception {
        Product productBD = this.findProduct(id);
        if(productBD.getState() == Product.ProductState.DISCONTINUED){
            throw new Exception("The product is already DISCONTINUED");
        }else{
            productBD.setState(Product.ProductState.DISCONTINUED);
            this.editProduct(productBD);
        }
    }

    @Override
    public Product findProduct(Long id) {

        return repoProduct.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        repoProduct.deleteById(id);
    }

}
