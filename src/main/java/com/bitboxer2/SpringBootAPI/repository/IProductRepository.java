package com.bitboxer2.SpringBootAPI.repository;


import com.bitboxer2.SpringBootAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
