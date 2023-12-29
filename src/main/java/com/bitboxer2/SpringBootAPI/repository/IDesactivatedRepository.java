package com.bitboxer2.SpringBootAPI.repository;

import com.bitboxer2.SpringBootAPI.model.Desactivated;
import com.bitboxer2.SpringBootAPI.model.PriceReduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDesactivatedRepository extends JpaRepository<Desactivated, Long> {
}
