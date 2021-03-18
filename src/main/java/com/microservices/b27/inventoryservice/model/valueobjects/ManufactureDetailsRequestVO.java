package com.microservices.b27.inventoryservice.model.valueobjects;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ManufactureDetailsRequestVO {

    private Long Id;
    private String city;
    private String state;
    private String pincode;

}
