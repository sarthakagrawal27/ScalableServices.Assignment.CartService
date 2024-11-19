package com.checkoutservice.app.demo.Repo;

import com.checkoutservice.app.demo.Models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
}
