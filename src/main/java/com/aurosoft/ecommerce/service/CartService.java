package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.Cart;
import com.aurosoft.ecommerce.entity.User;

import java.util.List;

public interface CartService {

    List<Cart> listAllCarts();

    List<Cart> getCartByUser(User user);
    Cart getCartById(int id);
    Cart insertCart(Cart cart);
    Cart updateCart(Cart cart);
    int deleteCart(int id);
}
