package com.microservices.b27.inventoryservice.exception;

public class ItemAlreadyExistsException extends Exception {

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
