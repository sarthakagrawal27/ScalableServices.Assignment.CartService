package com.checkoutservice.app.demo.Controller;

import com.checkoutservice.app.demo.Models.Wishlist;
import com.checkoutservice.app.demo.Repo.WishlistRepo;
import com.checkoutservice.app.demo.Service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Wishlist APIs", description = "API for handling wishlist items")
@RestController
@RequestMapping(value = "/wishlist")
public class WishlistController {
    @Autowired
    private WishlistRepo wishlistRepo;
    @Autowired
    private CartService cartService;

    @GetMapping(value = "/get")
    public List<Wishlist> getCartItems()
    {
        return wishlistRepo.findAll();
    }

    @PostMapping(value = "/add")
    public String addItems(@RequestBody Wishlist wishlist)
    {
        wishlistRepo.save(wishlist);
        return "Item Added to Wishlist!";
    }

    @PostMapping("/add/cart/{productId}")
    public String moveToCart(@PathVariable int productId) {
        boolean success = cartService.moveProductToCart(productId);
        if (success) {
            return "Product successfully moved from wishlist to cart";
        } else {
            return "Error occurred while moving product from wishlist to cart";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteItems(@PathVariable int id){
        Wishlist existingWishlist = wishlistRepo.findById(id).orElse(null);
        if (existingWishlist == null) {
            return "item not found with id: " + id;
        }
        wishlistRepo.delete(existingWishlist);
        return "Wishlist item deleted successfully with id: " + id;
    }
}
