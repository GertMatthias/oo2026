package ee.eljas.veebipood.controller;

import ee.eljas.veebipood.dto.OrderRowDto;
import ee.eljas.veebipood.entity.Order;
import ee.eljas.veebipood.entity.OrderRow;
import ee.eljas.veebipood.repository.OrderRepository;
import ee.eljas.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService orderService;

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
    public Order addOrders(@RequestParam Long personId, @RequestParam(required = false) String parcelMachine, @RequestBody List<OrderRowDto> orderRows){
        return orderService.saveOrder(personId, parcelMachine, orderRows); //Siin salvestab
        //return orderRepository.findAll(); //Siin on uuenenud seis
    }
}
