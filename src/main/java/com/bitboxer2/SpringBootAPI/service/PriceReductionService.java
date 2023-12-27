package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import com.bitboxer2.SpringBootAPI.repository.IPriceReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceReductionService implements IPriceReductionService{
    @Autowired
    private IPriceReductionRepository repoPriceReduction;
    @Override
    public void createPriceReduction(PriceReduction priceReduction) {
        repoPriceReduction.save(priceReduction);
    }

    @Override
    public List<PriceReduction> getPriceReductions() {

        List<PriceReduction> priceReductionList = repoPriceReduction.findAll();

        return priceReductionList;
    }

    @Override
    public void editPriceReduction(PriceReduction priceReduction) {
        this.createPriceReduction(priceReduction);
    }

    @Override
    public PriceReduction findPriceReduction(Long id) {
        return repoPriceReduction.findById(id).orElse(null);
    }

    @Override
    public void deletePriceReduction(Long id) {
        repoPriceReduction.deleteById(id);
    }

}
