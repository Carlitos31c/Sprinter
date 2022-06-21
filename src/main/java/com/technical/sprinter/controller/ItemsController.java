package com.technical.sprinter.controller;

import com.technical.sprinter.api.ItemsApi;
import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ItemsController implements ItemsApi {

    private final ItemService service;

    @Override
    public ResponseEntity<ItemDetails> createItemDetails(ItemDetails itemDetails) {
        log.info("Create item {}", itemDetails);
        return new ResponseEntity<>(service.createItem(itemDetails), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ItemDetails> deleteItemDetails(Long id) {
        log.info("Delete item with id {}", id);
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ItemDetails> modifyItemDetails(Long id, ItemDetails itemDetails) {
        log.info("Modify item {} with id {}", itemDetails, id);
        return new ResponseEntity<>(service.modifyItem(itemDetails), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ItemDetails> readItemDetails(Long id) {
        log.info("Find item with id {}", id);
        return new ResponseEntity<>(service.readItem(id), HttpStatus.OK);
    }
}
