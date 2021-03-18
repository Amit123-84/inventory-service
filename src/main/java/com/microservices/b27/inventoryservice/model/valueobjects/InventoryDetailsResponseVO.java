package com.microservices.b27.inventoryservice.model.valueobjects;


import com.microservices.b27.inventoryservice.model.entity.InventoryConfig;
import com.microservices.b27.inventoryservice.model.entity.ManufactureDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryDetailsResponseVO {

    private String itemName;
    private String manufacturer;
    private Double basePrice;
    private Double maxDiscountAllowed;
    private String countryCode;
    private Integer availableUnits;
    private InventoryConfigResponseVO inventoryConfig;
    private ManufactureDetailsResponseVO manufactureDetails;



}
