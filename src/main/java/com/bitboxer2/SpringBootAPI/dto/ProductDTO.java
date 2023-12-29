package com.bitboxer2.SpringBootAPI.dto;

import com.bitboxer2.SpringBootAPI.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter @Setter
public class ProductDTO {
    private Long productId;
    private String description;
    private double price;
    private Product.ProductState state;
    private Date creationDate;
    private String creator;

    public ProductDTO(Product pro) {
        this.productId = pro.getProductId();
        this.description = pro.getDescription();
        this.price = pro.getPrice();
        this.state = pro.getState();
        this.creationDate = pro.getCreationDate();
        this.creator = pro.getUser().getUsername();
    }
}
