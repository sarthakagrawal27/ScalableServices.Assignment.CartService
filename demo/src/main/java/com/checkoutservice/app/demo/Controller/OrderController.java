package com.checkoutservice.app.demo.Controller;

import com.checkoutservice.app.demo.DTO.OrderDTO;
import com.checkoutservice.app.demo.Models.Cart;
import com.checkoutservice.app.demo.Models.Order;
import com.checkoutservice.app.demo.Repo.OrderRepo;
import com.checkoutservice.app.demo.Service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Order APIs", description = "API for handling ordered items")
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping(value = "/get")
    public List<Order> getOrderedItems()
    {
        return orderRepo.findAll();
    }

    @GetMapping(value = "/get/{status}")
    public List<Order> getStatusBasedItems(@PathVariable String status)
    {
        return orderRepo.findByStatus(status);
    }

    @PostMapping(value = "/place")
    public String placeOrder(@RequestBody List<Cart> items)
    {
        boolean success = orderService.placeOrder(items);
        if (success) {
            return "Order placed successfully!";
        } else {
            return "Error occurred while placing the order";
        }
    }

    @PostMapping(value = "/cancel")
    public String placeOrder(@RequestBody Order items)
    {
        boolean success = orderService.cancelOrder(items);
        if (success) {
            return "Order cancelled successfully!";
        } else {
            return "Error occurred while cancelling the order";
        }
    }
}
