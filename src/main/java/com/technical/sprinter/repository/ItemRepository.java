package com.technical.sprinter.repository;

import com.technical.sprinter.entity.ItemEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<ItemEntity, Long> {
}
