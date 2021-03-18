package com.microservices.b27.inventoryservice.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name",nullable = false)
    private String itemName;

    @Column(name = "manufacturer",nullable = false)
    private String manufacturer;

    @Column(name = "base_price")
    private Double basePrice;

    @Column(name = "max_discount_allowed")
    private Double maxDiscountAllowed;

    @Column(name = "createdDate")
    private Date createdDate;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "available_units")
    private Integer availableUnits;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "config_id",nullable = false)
    private InventoryConfig inventoryConfig;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturing_id",nullable = false)
    private ManufactureDetails manufactureDetails;



}
