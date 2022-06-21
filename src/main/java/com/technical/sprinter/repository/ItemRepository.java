package com.technical.sprinter.repository;

import com.technical.sprinter.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemEntity, Long> {
}
