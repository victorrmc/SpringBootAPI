package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import java.util.*;

public interface IPriceReductionService {
    public void createPriceReduction(PriceReduction priceReduction);

    public List<PriceReduction> getPriceReductions();
    public void deletePriceReduction(Long id);
    public PriceReduction findPriceReduction(Long id);
    public void editPriceReduction(PriceReduction priceReduction);
}
