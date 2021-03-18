package com.microservices.b27.inventoryservice.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryConfigResponseVO {

    private Boolean canReturn;
    private Boolean sellable;
    private Boolean canReplace;
}
