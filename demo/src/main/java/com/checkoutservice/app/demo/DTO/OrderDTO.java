package com.checkoutservice.app.demo.DTO;

import com.checkoutservice.app.demo.Models.Cart;

import java.util.List;

public class OrderDTO {
    private List<Cart> items;

    // Getter and setter for quantity
    public List<Cart> getItems() {
        return items;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
    }
}
