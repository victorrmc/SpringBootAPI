package com.bitboxer2.SpringBootAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import java.util.*;


@Entity
@Getter @Setter
public class Product {
    public enum ProductState {
        ACTIVE, DISCONTINUED
    }
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long productId;
    @Column(nullable = false)
    private String description;
    private double price;
    private ProductState state;

    //@JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="Product_supplier",
            joinColumns=
            @JoinColumn(name="product_id", referencedColumnName="productId", nullable = false),
            inverseJoinColumns=
            @JoinColumn(name="supplier_id", referencedColumnName="supplierId",  nullable = false)
    )
    private List<Supplier> supplierList;

    @OneToOne
    @JoinColumn(name="priceReductionFK", referencedColumnName="priceReductionId")
    private PriceReduction priceReduction;
    private Date creationDate;
    private String creator;


}
