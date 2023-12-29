
package com.bitboxer2.SpringBootAPI.controller;

import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import com.bitboxer2.SpringBootAPI.service.IPriceReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

        import java.util.*;
@RestController
@CrossOrigin( origins = {"http://localhost:5173"})
public class PriceReductionController {

    @Autowired
    private IPriceReductionService priceReductionService;

    @GetMapping("/priceReductions/get")
    public List<PriceReduction> getPriceReductions(){
        return priceReductionService.getPriceReductions();
    }
    @PostMapping("/priceReductions/create")
    public String createPriceReduction(@RequestBody PriceReduction priceReduction){
        priceReductionService.createPriceReduction(priceReduction);
        return "The PriceReduction was created correctly";
    }

    @DeleteMapping("/priceReductions/delete/{id}")
    public String createPriceReduction(@PathVariable Long id){
        priceReductionService.deletePriceReduction(id);
        return "The PriceReduction was deleted correctly";
    }

    @PutMapping("/priceReductions/edit")
    public String editPriceReduction(@RequestBody PriceReduction priceReduction){
        priceReductionService.editPriceReduction(priceReduction);
        return "The PriceReduction was edited correctly";
    }

}
