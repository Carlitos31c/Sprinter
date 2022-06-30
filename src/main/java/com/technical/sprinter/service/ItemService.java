package com.technical.sprinter.service;

import com.technical.sprinter.model.ItemDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    ItemDetails createItem(ItemDetails item);

    void deleteItem(Long id);

    ItemDetails modifyItem(Long id, ItemDetails item);

    ItemDetails readItem(Long id);

    Page<ItemDetails> readAllItems(Pageable pageable);
}
