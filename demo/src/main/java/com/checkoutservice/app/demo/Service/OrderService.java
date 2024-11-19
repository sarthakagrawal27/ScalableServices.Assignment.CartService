package com.checkoutservice.app.demo.Service;

import com.checkoutservice.app.demo.Models.Cart;
import com.checkoutservice.app.demo.Models.Order;
import com.checkoutservice.app.demo.Repo.CartRepo;
import com.checkoutservice.app.demo.Repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepository;

    public boolean placeOrder(List<Cart> items) {
        for (Cart cartItem : items) {
            Order order = new Order();
            order.setProductId(cartItem.getProductId());
            order.setName(cartItem.getName());
            order.setDescription(cartItem.getDescription());
            order.setPrice(cartItem.getPrice());
            order.setStockQuantity(cartItem.getStockQuantity()); // You may want to adjust the quantity logic
            order.setStatus("PLACED");
            order.setCreatedAt(cartItem.getCreatedAt());
            order.setUpdatedAt(cartItem.getUpdatedAt());
            order.setImageUrl(cartItem.getImageUrl());
            orderRepo.save(order);
            cartRepository.delete(cartItem);
        }
        return true;
    }

    public boolean cancelOrder(Order item) {
        item.setStatus("CANCELLED");
        orderRepo.save(item);
        return true;
    }
}
