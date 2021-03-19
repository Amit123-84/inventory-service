package com.microservices.b27.inventoryservice.service;

import com.microservices.b27.inventoryservice.exception.InventoryBadRequestException;
import com.microservices.b27.inventoryservice.exception.ItemAlreadyExistsException;
import com.microservices.b27.inventoryservice.exception.ItemNotFoundException;
import com.microservices.b27.inventoryservice.model.constants.ResponseConstants;
import com.microservices.b27.inventoryservice.model.entity.InventoryConfig;
import com.microservices.b27.inventoryservice.model.entity.InventoryDetails;
import com.microservices.b27.inventoryservice.model.entity.ManufactureDetails;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryConfigResponseVO;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsRequestVO;
import com.microservices.b27.inventoryservice.model.valueobjects.InventoryDetailsResponseVO;
import com.microservices.b27.inventoryservice.model.valueobjects.ManufactureDetailsResponseVO;
import com.microservices.b27.inventoryservice.repository.InventoryDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InventoryServiceImpl implements InventoryService{

    private final InventoryDetailsRepository inventoryDetailsRepository;

    @Override
    public InventoryDetailsResponseVO addItem(InventoryDetailsRequestVO inventoryDetailsRequestVO) throws ItemAlreadyExistsException, InventoryBadRequestException {
        InventoryDetailsResponseVO inventoryDetailsResponseVO = new InventoryDetailsResponseVO();
        Optional<InventoryDetails> existingItemDetails = inventoryDetailsRepository
                .findByItemNameAndManufacturer(inventoryDetailsRequestVO.getItemName(),inventoryDetailsRequestVO.getManufacturer());
        if (existingItemDetails.isPresent()) {
            throw new ItemAlreadyExistsException(ResponseConstants.EXISTING_ITEM.name);
        }
        InventoryDetails inventoryDetails= constructInventoryDetails(inventoryDetailsRequestVO);
        try {
            inventoryDetails=inventoryDetailsRepository.save(inventoryDetails);
        } catch (DataIntegrityViolationException ex) {
            throw new InventoryBadRequestException(ex.getMessage());
        }
        return constructInventoryDetailsResponseVO(inventoryDetails);
    }

    @Override
    public InventoryDetailsResponseVO getItem(String itemName) throws ItemNotFoundException {
        Optional<InventoryDetails> inventoryDetails = inventoryDetailsRepository.findByItemName(itemName);
        if (inventoryDetails.isEmpty()) {
            throw new ItemNotFoundException(ResponseConstants.NOT_FOUND.name + itemName);
        }
        return constructInventoryDetailsResponseVO(inventoryDetails.get());
    }

    @Override
    public void deleteItem(String itemName) throws ItemNotFoundException {
        Optional<InventoryDetails> inventoryDetails = inventoryDetailsRepository.findByItemName(itemName);
        if (!inventoryDetails.isPresent()) {
            throw new ItemNotFoundException(ResponseConstants.NOT_FOUND.name + itemName);
        }
        inventoryDetailsRepository.deleteById(inventoryDetails.get().getItemId());

    }

    private InventoryDetails constructInventoryDetails(InventoryDetailsRequestVO inventoryDetailsRequestVO){
        InventoryDetails inventoryDetails=new InventoryDetails();
        BeanUtils.copyProperties(inventoryDetailsRequestVO, inventoryDetails);
        inventoryDetails.setCreatedDate(new Date());
        if (inventoryDetailsRequestVO.getInventoryConfig() != null) {
            InventoryConfig inventoryConfig = InventoryConfig.builder()
                    .canReplace(inventoryDetailsRequestVO.getInventoryConfig().getCanReplace()==null?Boolean.FALSE:Boolean.valueOf(inventoryDetailsRequestVO.getInventoryConfig().getCanReplace()))
                    .canReturn(inventoryDetailsRequestVO.getInventoryConfig().getCanReturn()==null?Boolean.FALSE:inventoryDetailsRequestVO.getInventoryConfig().getCanReturn())
                    .sellable(inventoryDetailsRequestVO.getInventoryConfig().getSellable()==null?Boolean.FALSE:inventoryDetailsRequestVO.getInventoryConfig().getSellable()).build();
            inventoryDetails.setInventoryConfig(inventoryConfig);
        }

        if (inventoryDetailsRequestVO.getManufactureDetails() != null) {
            ManufactureDetails manufactureDetails = ManufactureDetails.builder()
                    .city(inventoryDetailsRequestVO.getManufactureDetails().getCity())
                    .pincode(inventoryDetailsRequestVO.getManufactureDetails().getPincode())
                    .state(inventoryDetailsRequestVO.getManufactureDetails().getState()).build();
            inventoryDetails.setManufactureDetails(manufactureDetails);
        }

        return inventoryDetails;


    }

    private InventoryDetailsResponseVO constructInventoryDetailsResponseVO(InventoryDetails inventoryDetails){
        InventoryDetailsResponseVO inventoryDetailsResponseVO=new InventoryDetailsResponseVO();
        BeanUtils.copyProperties(inventoryDetails, inventoryDetailsResponseVO);
        if (inventoryDetails.getInventoryConfig() != null) {
            InventoryConfigResponseVO inventoryConfigResponseVO = InventoryConfigResponseVO.builder()
                    .canReplace(inventoryDetails.getInventoryConfig().getCanReplace())
                    .canReturn(inventoryDetails.getInventoryConfig().getCanReturn())
                    .sellable(inventoryDetails.getInventoryConfig().getSellable()).build();
            inventoryDetailsResponseVO.setInventoryConfig(inventoryConfigResponseVO);
        }

        if (inventoryDetails.getManufactureDetails() != null) {
            ManufactureDetailsResponseVO manufactureDetailsResponseVO = ManufactureDetailsResponseVO.builder()
                    .city(inventoryDetails.getManufactureDetails().getCity())
                    .pincode(inventoryDetails.getManufactureDetails().getPincode())
                    .state(inventoryDetails.getManufactureDetails().getState()).build();
            inventoryDetailsResponseVO.setManufactureDetails(manufactureDetailsResponseVO);
        }

        return inventoryDetailsResponseVO;


    }
}
