package com.example.products.service;

import com.example.products.entities.Order;
import com.example.products.entities.Product;
import com.example.products.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path="/ping")
    public @ResponseBody String ping(){
        return "pong";
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewOrder(@RequestParam String client,
                                              @RequestParam LocalDate date){
        Order o = new Order();;
        o.setClient(client);
        o.setDate(date);
        orderRepository.save(o);
        return "Saved";
    }

}
