package com.bitboxer2.SpringBootAPI.controller;

import com.bitboxer2.SpringBootAPI.model.Supplier;
import com.bitboxer2.SpringBootAPI.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@CrossOrigin( origins = {"http://localhost:5173"})
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/suppliers/get")
    public List<Supplier> getSuppliers(){
        return supplierService.getSuppliers();
    }

    @PostMapping("/suppliers/create")
    public String createSupplier(@RequestBody Supplier supplier){
        supplierService.createSupplier(supplier);
        return "The Supplier was created correctly";
    }

    @DeleteMapping("/suppliers/delete/{id}")
    public String createSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return "The Supplier was deleted correctly";
    }

    @PutMapping("/suppliers/edit")
    public String editSupplier(@RequestBody Supplier supplier){
        supplierService.editSupplier(supplier);
        return "The Supplier was edited correctly";
    }

}
