package ee.eljas.veebipood.controller;

import ee.eljas.veebipood.entity.Order;
import ee.eljas.veebipood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrders(@PathVariable Long id){
        orderRepository.deleteById(id); //Kustutan
        return orderRepository.findAll(); //Uuenenud seis
    }

    @PostMapping("orders")
    public List<Order> addOrders(@RequestBody Order order){
        orderRepository.save(order); //Siin salvestab
        return orderRepository.findAll(); //Siin on uuenenud seis
    }
}
