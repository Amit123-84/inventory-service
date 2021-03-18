package com.microservices.b27.inventoryservice.model.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class InventoryConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id")
    private Long Id;

    @Column(name = "can_return")
    private Boolean canReturn;

    @Column(name = "sellable")
    private Boolean sellable;

    @Column(name = "can_replace")
    private Boolean canReplace;

}
