package com.example.cartService.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CartNoteFound extends  RuntimeException {
    private long errorCode;
    private String statusMessage;

    public CartNoteFound(long errorCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}
