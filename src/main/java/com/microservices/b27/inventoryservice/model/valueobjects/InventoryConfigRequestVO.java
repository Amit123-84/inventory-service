package com.microservices.b27.inventoryservice.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InventoryConfigRequestVO {

    private Long Id;
    private Boolean canReturn=Boolean.FALSE;
    private Boolean sellable=Boolean.FALSE;
    private Boolean canReplace=Boolean.FALSE;
}
