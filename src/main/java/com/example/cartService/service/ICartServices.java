package com.example.cartService.service;

import com.example.cartService.model.CartModel;
import com.example.cartService.util.Response;

public interface ICartServices {
    public Response addToCart(String token,long bookId,int quantity);
    public Response removeCart(String token,long cartId,long bookId);
    public Response updateQuantity(String token,long bookId,long cartId,int quantity);
    public Response getAllCartOfUser(String token);
    public CartModel getCart(String token,long car);
}
