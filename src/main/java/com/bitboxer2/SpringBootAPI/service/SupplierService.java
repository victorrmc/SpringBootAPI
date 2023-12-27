package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import com.bitboxer2.SpringBootAPI.model.Product;
import com.bitboxer2.SpringBootAPI.model.Supplier;
import com.bitboxer2.SpringBootAPI.repository.ISupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SupplierService implements ISupplierService{
    @Autowired
    private ISupplierRepository repoSupplier;
    @Override
    public void createSupplier(Supplier supplier) {
        repoSupplier.save(supplier);
    }

    @Override
    public List<Supplier> getSuppliers() {

        List<Supplier> supplierList = repoSupplier.findAll();

        return supplierList;
    }

    @Override
    public void editSupplier(Supplier supplier) {
        this.createSupplier(supplier);
    }

    @Override
    public Supplier findSupplier(Long id) {
       return repoSupplier.findById(id).orElse(null);
    }

    @Override
    public void deleteSupplier(Long id) {
        repoSupplier.deleteById(id);
    }

}
