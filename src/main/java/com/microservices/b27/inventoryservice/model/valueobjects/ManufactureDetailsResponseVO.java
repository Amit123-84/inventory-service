package com.microservices.b27.inventoryservice.model.valueobjects;


import com.microservices.b27.inventoryservice.model.entity.InventoryConfig;
import com.microservices.b27.inventoryservice.model.entity.ManufactureDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ManufactureDetailsResponseVO {

    private String city;
    private String state;
    private String pincode;
}
