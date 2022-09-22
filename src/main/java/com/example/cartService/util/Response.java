package com.example.cartService.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * purpose: Creating a response class
 * @author Anuj Solanki
 */
@Data
@AllArgsConstructor
public class Response {
    private String statusMsg;
    private long statusCode;
    private Object object;

    public Response() {

    }
}
