package com.bitboxer2.SpringBootAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long supplierId;
    private String name;
    private String country;

   // @JsonBackReference
    @JsonIgnore
    @ManyToMany(mappedBy="supplierList")
    private List<Product> productList;


}
