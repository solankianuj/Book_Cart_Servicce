package com.example.cartService.service;

import com.example.cartService.exception.CartNoteFound;
import com.example.cartService.model.CartModel;
import com.example.cartService.repository.CartRepository;
import com.example.cartService.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CartServices implements ICartServices{

    @Autowired
    Token tokenUtil;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CartRepository cartRepository;

    @Override
    public Response addToCart(String token, long bookId,int quantity) {
        if (isUserPresent(token)!=null){
            CartModel cartModel=new CartModel();
            cartModel.setBookId(bookId);
            cartModel.setUserId(isUserPresent(token).getUserId());
            cartModel.setQuantity(quantity);
            double Price=isBookPresent(bookId).getPrice();
            double totalPrice=Price*quantity;
            cartModel.setTotalPrice(totalPrice);
            int newQuantity=isBookPresent(bookId).getQuantity()-quantity;
            setBookQuantity(bookId,newQuantity);
            cartRepository.save(cartModel);
            return new Response("Cart added successfully", 200, cartModel);
        }
        throw new CartNoteFound(400,"User Not Found !");
    }

    @Override
    public Response removeCart (String token,long cartId,long bookId){
        if (isUserPresent(token)!=null) {
            Optional<CartModel> cartModel = cartRepository.findById(cartId);
            if (cartModel.isPresent()) {
                cartRepository.delete(cartModel.get());
                int newQuantity = isBookPresent(bookId).getQuantity() + cartModel.get().getQuantity();
                setBookQuantity(bookId, newQuantity);
                return new Response("Cart removed successfully", 200, cartModel.get());
            }
            throw new CartNoteFound(400,"Cart Not Found !");
        }
        throw new CartNoteFound(400,"User Not Found !");
    }

    @Override
    public Response updateQuantity(String token,long bookId, int quantity) {
        return null;
    }

    @Override
    public Response getAllCartOfUser(String token) {
        if (isUserPresent(token) != null) {
            Long userId = tokenUtil.decodeToken(token);
            List<CartModel> cartModelList = cartRepository.findAll().stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList());
            return new Response("Fetching all cart of user", 200, cartModelList);
        }
        throw new CartNoteFound(400,"User Not Found !");
    }

    public BookStoreUser isUserPresent(String token){
        return restTemplate.getForObject("http://localhost:9091/user/verify/"+token,BookStoreUser.class);
    }
    public BookModel isBookPresent(long bookId){
        return restTemplate.getForObject("http://localhost:9092/book/getBook/"+bookId,BookModel.class);
    }
    public BookModel setBookQuantity(long bookId,int quantity){
        return restTemplate.getForObject("http://localhost:9092/book/changeBookQuantity/{bookId}/{quantity}",BookModel.class,bookId,quantity);
    }
}
