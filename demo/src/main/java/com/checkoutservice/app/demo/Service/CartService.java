package com.checkoutservice.app.demo.Service;

import com.checkoutservice.app.demo.Models.Cart;
import com.checkoutservice.app.demo.Models.Wishlist;
import com.checkoutservice.app.demo.Repo.CartRepo;
import com.checkoutservice.app.demo.Repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    @Autowired
    private WishlistRepo wishlistRepository;
    @Autowired
    private CartRepo cartRepository;

    public boolean moveProductToCart(int productId) {
        // Check if the product exists in the wishlist for the given user
        Wishlist wishlistItem = wishlistRepository.findById(productId).orElse(null);;
        if (wishlistItem == null) {
            return false;
        }
        // Remove product from wishlist
        wishlistRepository.delete(wishlistItem);

        // Add product to cart
        Cart cartItem = new Cart();
        cartItem.setProductId(productId);
        cartItem.setName(wishlistItem.getName());
        cartItem.setDescription(wishlistItem.getDescription());
        cartItem.setPrice(wishlistItem.getPrice());
        cartItem.setStockQuantity(1);
        cartItem.setCreatedAt(LocalDateTime.now());
        cartRepository.save(cartItem);
        return true;
    }
}
