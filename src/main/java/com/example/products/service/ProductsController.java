package com.example.products.service;


import com.example.products.entities.Order;
import com.example.products.entities.Product;
import com.example.products.repositories.OrderRepository;
import com.example.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping(path="/ping")
    public @ResponseBody String ping(){
        return "pong";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewProduct(@RequestParam String name,
                                              @RequestParam boolean isExist,
                                              @RequestParam Integer price){
        Product p = new Product();
//        p.setId(1);
        p.setName(name);
        p.setExist(isExist);
        p.setPrice(price);
        productRepository.save(p);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }

    @PostMapping(path="/byId")
    public @ResponseBody Product getProductById(@RequestParam Integer id){
        Optional<Product> p = productRepository.getProductsById(id);
        return p.orElse(null);
    }

    @PostMapping(path="/byName")
    public @ResponseBody Product getProductByName(@RequestParam String name){
        Optional<Product> p = productRepository.getProductsByName(name);
        return p.orElse(null);
    }

    @PostMapping(path="/delete")
    public @ResponseBody String deleteProduct(@RequestParam Integer id) {
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            productRepository.delete(p.get());
            return "Product " + p.get().getName() + " is deleted";
        } else return "Product with id " + id + " is not found";
    }

    @PostMapping(path="/update")
    public @ResponseBody String updateProduct (@RequestParam Integer id,
                                               @RequestParam String name,
                                               @RequestParam boolean isExist,
                                               @RequestParam Integer price) {
        Optional<Product> p = productRepository.findById(id);

        if (p.isPresent()) {
            productRepository.delete(p.get());
            Product p1 = new Product();
            p1.setName(name);
            p1.setExist(isExist);
            p1.setPrice(price);
            p1.setId(id);
            productRepository.save(p1);
            return "Product parameters with id " + id + " have been changed";
        } else return "Product with id " + id + " is not found";
    }

    @PostMapping(path="/addProductInOrder")
    public @ResponseBody String formAnOrder(@RequestParam Integer orderId,
                                            Product p){
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isPresent()) {
            p.setOrder(o.get());
            productRepository.save(p);
            return "Product " + p.getName() + "added to the order with id " + o.get().getId();
        } else return "order with " + orderId + " is not found";
    }
}
