package com.technical.sprinter.controller;

import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
@Slf4j
public class ItemsController {

    private final ItemService service;

    @PostMapping
    public ResponseEntity<ItemDetails> createItemDetails(@Valid @RequestBody ItemDetails itemDetails) {
        log.info("Create item {}", itemDetails);
        return new ResponseEntity<>(service.createItem(itemDetails), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDetails> deleteItemDetails(@PathVariable("id") Long id) {
        log.info("Delete item with id {}", id);
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDetails> modifyItemDetails(@PathVariable("id") Long id,
                                                         @Valid @RequestBody ItemDetails itemDetails) {
        log.info("Modify item {} with id {}", itemDetails, id);
        return new ResponseEntity<>(service.modifyItem(id, itemDetails), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetails> readItemDetails(@PathVariable("id") Long id) {
        log.info("Find item with id {}", id);
        return new ResponseEntity<>(service.readItem(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ItemDetails>> readAllItemDetails(Pageable pageable) {
        log.info("Find all items detail");
        return new ResponseEntity<>(service.readAllItems(pageable), HttpStatus.OK);
    }
}
