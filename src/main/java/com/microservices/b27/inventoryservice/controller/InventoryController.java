package com.microservices.b27.inventoryservice.controller;

import com.microservices.b27.inventoryservice.exception.InventoryBadRequestException;
import com.microservices.b27.inventoryservice.exception.ItemNotFoundException;
import com.microservices.b27.inventoryservice.model.constants.ResponseConstants;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsRequestVO;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsResponseVO;
import com.microservices.b27.inventoryservice.service.InventoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    @Autowired
    InventoryService inventoryService;


    @GetMapping("/getInventoryDetails/{itemName}")
    public ResponseEntity<InventoryDetailsResponseVO> getProductInventoryDetails(@PathVariable String itemName)
            throws ItemNotFoundException, InventoryBadRequestException {
        if(StringUtils.isEmpty(itemName)){
            throw  new InventoryBadRequestException(ResponseConstants.NOT_FOUND.name+ itemName);
        }
        return ResponseEntity.ok(inventoryService.getItem(itemName));
    }


   @PostMapping("/addInventoryDetails")
    public ResponseEntity<InventoryDetailsResponseVO> addItem(@RequestBody InventoryDetailsRequestVO request) throws Exception {
        return ResponseEntity.ok(inventoryService.addItem(request));

    }



   /* @PutMapping({"/bank/updateCustomerDetails", "/bank/updateCustomerDetails/{customerId}"})
    public ResponseEntity<CustomerDetails> updateCustomer(@PathVariable Optional<Long> customerId,
                                                          @RequestBody UpdateCustomerDetailsVO request)
            throws CustomerBadRequestException, CustomerNotFoundException {
        if (customerId.isEmpty()) {
            throw new CustomerBadRequestException(ResponseConstants.ID_EMPTY.name);
        }
        request.setCustomerId(customerId.get());
        return ResponseEntity.ok(bankService.updateCustomer(request));
    }



    @DeleteMapping({"/bank/customerDetails", "/bank/customerDetails/{customerId}"})
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Optional<Long> customerId)
            throws CustomerNotFoundException {
        if (customerId.isEmpty()) {
            bankService.deleteAll();
            return ResponseEntity.ok().build();
        } else {
            bankService.deleteById(customerId.get());
            return ResponseEntity.accepted().build();
        }
    }
*/

}
