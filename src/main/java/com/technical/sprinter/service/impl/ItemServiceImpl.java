package com.technical.sprinter.service.impl;

import com.technical.sprinter.entity.ItemEntity;
import com.technical.sprinter.exception.ConflictException;
import com.technical.sprinter.exception.ExceptionCodes;
import com.technical.sprinter.exception.NotFoundException;
import com.technical.sprinter.mapper.ItemMapper;
import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.repository.ItemRepository;
import com.technical.sprinter.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Value("${sprinter.cache.sleep}")
    private Long cacheSleepMilis;

    private final ItemRepository repository;
    private final ItemMapper mapper;

    @Override
    public ItemDetails createItem(ItemDetails item) {
        return mapper.mapToDto(repository.save(mapper.mapToEntitySave(item)));
    }

    @Override
    public void deleteItem(Long id) {
        this.readItem(id); // Check if exists
        repository.deleteById(id);
    }

    @Override
    public ItemDetails modifyItem(Long id, ItemDetails item) {
        if (!Objects.equals(id, item.getId())) {
            throw new ConflictException(String.format(ExceptionCodes.CONFLICT_EXCEPTION.getMessage(), id, item.getId()));
        }

        ItemDetails savedItem = this.readItem(id);
        savedItem = mapper.mapToEntityUpdate(savedItem, item);

        return mapper.mapToDto(repository.save(mapper.mapToEntity(savedItem)));
    }

    @SneakyThrows
    @Override
    public ItemDetails readItem(Long id) {
        ItemEntity item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ExceptionCodes.NOT_FOUND_EXCEPTION.getMessage(), id)));

        return mapper.mapToDto(item);
    }

    @SneakyThrows
    @Cacheable("items")
    @Override
    public Page<ItemDetails> readAllItems(Pageable pageable) {
        Thread.sleep(cacheSleepMilis); // To test cache in the first iteration

        return repository.findAll(pageable)
                .map(mapper::mapToDto);
    }
}
