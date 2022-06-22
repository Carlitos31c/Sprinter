package com.technical.sprinter.service.impl;

import com.sun.javafx.binding.StringFormatter;
import com.technical.sprinter.entity.ItemEntity;
import com.technical.sprinter.exception.NotFoundException;
import com.technical.sprinter.mapper.ItemMapper;
import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.repository.ItemRepository;
import com.technical.sprinter.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Override
    public ItemDetails createItem(ItemDetails item) {
        return mapper.mapToDto(repository.save(mapper.mapToEntity(item)));
    }

    @Override
    public void deleteItem(Long id) {
        this.readItem(id); // Check if exists
        repository.deleteById(id);
    }

    @Override
    public ItemDetails modifyItem(ItemDetails item) {
        return mapper.mapToDto(repository.save(mapper.mapToEntity(item)));
    }

    @SneakyThrows
    @Override
    public ItemDetails readItem(Long id) {
        ItemEntity item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Item not found %d", id)));

        return mapper.mapToDto(item);
    }

    @Override
    public Page<ItemDetails> readAllItems(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::mapToDto);
    }
}
