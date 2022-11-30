package com.example.products.repositories;

import com.example.products.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> getProductsById(Integer id);

    Optional<Product> getProductsByName(String name);

}
