package com.microservices.b27.inventoryservice.exception;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
