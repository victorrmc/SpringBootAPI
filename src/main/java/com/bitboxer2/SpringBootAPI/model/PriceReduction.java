package com.bitboxer2.SpringBootAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter @Setter
public class PriceReduction {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long priceReductionId;
    private Double reducedPrice;
    private Date startDate;
    private Date endDate;

}
