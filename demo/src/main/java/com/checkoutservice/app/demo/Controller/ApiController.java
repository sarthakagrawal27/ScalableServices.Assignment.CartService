package com.checkoutservice.app.demo.Controller;

import com.checkoutservice.app.demo.DTO.CartDTO;
import com.checkoutservice.app.demo.Models.Cart;
import com.checkoutservice.app.demo.Repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Cart APIs", description = "API for handling cart items")
@RestController
@RequestMapping(value = "/cart")
public class ApiController {

    @Autowired
    private CartRepo cartRepo;

    @GetMapping(value = "/")
    public String getPage()
    {
        return "Welcome";
    }

    @GetMapping(value = "/get")
    public List<Cart> getCartItems()
    {
        return cartRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addItems(@RequestBody Cart cart)
    {
        cartRepo.save(cart);
        return "Item Added!";
    }

    @PutMapping(value = "/update/{id}")
    public String updateItems(@PathVariable int id, @RequestBody Cart cart)
    {
        Cart existingCart = cartRepo.findById(id).orElse(null);
        if (existingCart == null) {
            return "Cart item not found with id: " + id;
        }
        existingCart.setName(cart.getName());
        existingCart.setDescription(cart.getDescription());
        existingCart.setPrice(cart.getPrice());
        existingCart.setStockQuantity(cart.getStockQuantity());
        existingCart.setImageUrl(cart.getImageUrl());
        existingCart.setUpdatedAt(LocalDateTime.now());

        cartRepo.save(existingCart);

        return "Cart item updated successfully with id: " + id;
    }

    @PutMapping(value = "/update/quantity/{id}")
    public String updateQuantity(@PathVariable int id, @RequestBody CartDTO cartDTO) {
        // Find the cart item by ID
        Cart existingCart = cartRepo.findById(id).orElse(null);

        if (existingCart == null) {
            return "Cart item not found with id: " + id;
        }
        existingCart.setStockQuantity(cartDTO.getQuantity());
        existingCart.setUpdatedAt(LocalDateTime.now());
        cartRepo.save(existingCart);
        return "Cart item quantity updated successfully with id: " + id;
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteItems(@PathVariable int id){
        Cart existingCart = cartRepo.findById(id).orElse(null);
        if (existingCart == null) {
            return "Cart item not found with id: " + id;
        }
        cartRepo.delete(existingCart);
        return "Cart item deleted successfully with id: " + id;
    }
}
