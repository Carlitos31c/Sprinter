package com.technical.sprinter.service;

import com.technical.sprinter.model.ItemDetails;

public interface ItemService {

    ItemDetails createItem(ItemDetails item);

    void deleteItem(Long id);

    ItemDetails modifyItem(ItemDetails item);

    ItemDetails readItem(Long id);
}
