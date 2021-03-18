package com.microservices.b27.inventoryservice.model.constants;

public enum ResponseConstants {
    NAME_EMPTY("Please Enter Item Name"),
    EXISTING_ITEM("Item for a manufacturer Already Exists"),
    NOT_FOUND("Item not found  : ");

    public final String name;

    ResponseConstants(String name) {
        this.name = name;
    }
}
