package com.checkoutservice.app.demo.Repo;

import com.checkoutservice.app.demo.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(String status);
}
