package com.aweglicki.app.repositories;

import com.aweglicki.app.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByProductName(String productName);
}
