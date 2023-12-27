package com.bitboxer2.SpringBootAPI.repository;


import com.bitboxer2.SpringBootAPI.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
}
