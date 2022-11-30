package com.example.products.repositories;

import com.example.products.entities.Order;
import com.example.products.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> getOrderById(Integer id);
}
