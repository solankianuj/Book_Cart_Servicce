package com.example.cartService.controller;

import com.example.cartService.model.CartModel;
import com.example.cartService.service.CartServices;
import com.example.cartService.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Purpose-Creating cart service operation APIs.
 * @author anuj solanki
 * @date 20/09/2022
 * @version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServices cartServices;

    /**
     * Purpose-API to add to cart.
     * @param token
     * @param bookId
     * @param quantity
     * @return
     */
    @PostMapping("/addToCart")
    public ResponseEntity<Response> addToCart(@RequestHeader String token, @RequestParam long bookId,@RequestParam int quantity){
        Response response=cartServices.addToCart(token,bookId,quantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to remove from cart.
     * @param token
     * @param cartId
     * @param bookId
     * @return
     */
    @DeleteMapping("/removeFromCart")
    public ResponseEntity<Response> removeCart(@RequestHeader String token,@RequestParam long cartId,@RequestParam long bookId){
       Response response=cartServices.removeCart(token,cartId,bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to update book quantity in cart.
     * @param token
     * @param bookId
     * @param cartId
     * @param quantity
     * @return
     */
    @PutMapping("/updateQuantity")
    public ResponseEntity<Response> updatingQuantity(@RequestHeader String token,@RequestParam long bookId,@RequestParam long cartId,@RequestParam int quantity){
        Response response=cartServices.updateQuantity(token,bookId,cartId,quantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch user's carts.
     * @param token
     * @return
     */
    @GetMapping("/fetchCart")
    public ResponseEntity<Response> fetchingCart(@RequestHeader String token){
        Response response=cartServices.getAllCartOfUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Purpose-API to fetch cart.
     * @param token
     * @param cartId
     * @return
     */
    @GetMapping("/getCart/{token}/{cartId}")
    public ResponseEntity<CartModel> fetchingCart(@PathVariable String token,@PathVariable long cartId){
        CartModel response=cartServices.getCart(token,cartId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
