package com.raka.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raka.order.model.Order;
import com.raka.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping()
  public List <Order> getAllOrder() {
      return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable Long id){
    Order order = orderService.getOrderById(id);
    return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public Order createOrder(@RequestBody Order order) {
      return orderService.createOrder(order);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long id){
    return ResponseEntity.ok().build();
  }

}
