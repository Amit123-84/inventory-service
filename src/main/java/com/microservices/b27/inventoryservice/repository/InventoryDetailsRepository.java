package com.microservices.b27.inventoryservice.repository;

import com.microservices.b27.inventoryservice.model.entity.InventoryDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryDetailsRepository extends JpaRepository<InventoryDetails, Long> {
    Optional<InventoryDetails> findByItemNameAndManufacturer (String itemName, String manufacturer);

    Optional<InventoryDetails> findByItemName(String itemName);
}
