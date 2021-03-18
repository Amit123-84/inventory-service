package com.microservices.b27.inventoryservice.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryDetailsRequestVO implements Serializable {

    private Long itemId;
    private String itemName;
    private String manufacturer;
    private Double basePrice;
    private Double maxDiscountAllowed;
    private Date createdDate;
    private Date updatedDate;
    private String countryCode;
    private Integer availableUnits;
    private InventoryConfigRequestVO inventoryConfig;
    private ManufactureDetailsRequestVO manufactureDetails;



}
