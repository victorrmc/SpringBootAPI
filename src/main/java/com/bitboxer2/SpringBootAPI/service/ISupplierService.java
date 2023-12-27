package com.bitboxer2.SpringBootAPI.service;

import com.bitboxer2.SpringBootAPI.model.Supplier;
import java.util.*;

public interface ISupplierService {
    public void createSupplier(Supplier supplier);

    public List<Supplier> getSuppliers();
    public void deleteSupplier(Long id);
    public Supplier findSupplier(Long id);
    public void editSupplier(Supplier supplier);

}
