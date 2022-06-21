package com.technical.sprinter.controller;

import com.technical.sprinter.api.ItemsApi;
import com.technical.sprinter.model.ItemDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ItemsController implements ItemsApi {

    @Override
    public ResponseEntity<ItemDetails> createItemDetails(ItemDetails itemDetails) {
        return ItemsApi.super.createItemDetails(itemDetails);
    }

    @Override
    public ResponseEntity<ItemDetails> deleteItemDetails(String id) {
        return ItemsApi.super.deleteItemDetails(id);
    }

    @Override
    public ResponseEntity<ItemDetails> modifyItemDetails(String id, ItemDetails itemDetails) {
        return ItemsApi.super.modifyItemDetails(id, itemDetails);
    }

    @Override
    public ResponseEntity<ItemDetails> readItemDetails(String id) {
        return ItemsApi.super.readItemDetails(id);
    }
}
