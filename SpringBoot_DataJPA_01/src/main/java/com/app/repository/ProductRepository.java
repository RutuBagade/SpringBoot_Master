package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByProdCode(String prodCode);
}
