package com.microservices.b27.inventoryservice.controller;

import com.microservices.b27.inventoryservice.exception.InventoryBadRequestException;
import com.microservices.b27.inventoryservice.exception.ItemNotFoundException;
import com.microservices.b27.inventoryservice.model.constants.ResponseConstants;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsRequestVO;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsResponseVO;
import com.microservices.b27.inventoryservice.service.InventoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    private Logger logger= LoggerFactory.getLogger(InventoryController.class);

    @GetMapping("/inventoryService/getInventoryDetails/{itemName}")
    public ResponseEntity<InventoryDetailsResponseVO> getProductInventoryDetails(@PathVariable String itemName)
            throws ItemNotFoundException, InventoryBadRequestException {
        if(StringUtils.isEmpty(itemName)){
            throw  new InventoryBadRequestException(ResponseConstants.NOT_FOUND.name+ itemName);
        }
        return ResponseEntity.ok(inventoryService.getItem(itemName));
    }


   @PostMapping("/inventoryService/addInventoryDetails")
    public ResponseEntity<InventoryDetailsResponseVO> addItem(@RequestBody InventoryDetailsRequestVO request) throws Exception {
        return ResponseEntity.ok(inventoryService.addItem(request));

    }

    @DeleteMapping("/inventoryService/deleteItem/{itemName}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable String itemName) throws InventoryBadRequestException, ItemNotFoundException {
                 if(StringUtils.isEmpty(itemName)){
                     throw  new InventoryBadRequestException(ResponseConstants.NOT_FOUND.name+ itemName);
                 }
            inventoryService.deleteItem(itemName);
            return ResponseEntity.accepted().build();

    }

    @GetMapping("/inventoryService/getPromotion")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String getPromotionDetailsRetry(){
        logger.info("Get Promotion Invoked");
        ResponseEntity<String> stringResponseEntity= new RestTemplate().getForEntity("dummyUrl",String.class);
        return stringResponseEntity.getBody();
    }

    private String fallbackResponse(Exception ex){
        return "DummyFallbackResponse";
    }


}
