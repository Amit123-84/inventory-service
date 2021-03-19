package com.microservices.b27.inventoryservice.service;

import com.microservices.b27.inventoryservice.exception.InventoryBadRequestException;
import com.microservices.b27.inventoryservice.exception.ItemAlreadyExistsException;
import com.microservices.b27.inventoryservice.exception.ItemNotFoundException;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsRequestVO;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsResponseVO;

public interface InventoryService {
    InventoryDetailsResponseVO addItem(InventoryDetailsRequestVO inventoryDetailsRequestVO) throws ItemAlreadyExistsException, InventoryBadRequestException;

    InventoryDetailsResponseVO getItem(String itemName) throws ItemNotFoundException;

    void deleteItem(String  itemName) throws ItemNotFoundException;
}
