package com.technical.sprinter.service;

import com.technical.sprinter.domain.ItemDetailMother;
import com.technical.sprinter.domain.ItemEntityMother;
import com.technical.sprinter.domain.LongMother;
import com.technical.sprinter.entity.ItemEntity;
import com.technical.sprinter.exception.ConflictException;
import com.technical.sprinter.exception.NotFoundException;
import com.technical.sprinter.mapper.ItemMapper;
import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.repository.ItemRepository;
import com.technical.sprinter.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceImplTest {

    @Mock
    private ItemRepository repository;

    @Mock
    private ItemMapper mapper;

    @InjectMocks
    private ItemServiceImpl service;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "cacheSleepMilis", 1000L);
    }

    @Test
    public void shouldCreateItem() {
        when(mapper.mapToEntitySave(any(ItemDetails.class)))
                .thenReturn(ItemEntityMother.random());
        when(repository.save(any(ItemEntity.class)))
                .thenReturn(ItemEntityMother.random());
        when(mapper.mapToDto(any(ItemEntity.class)))
                .thenReturn(ItemDetailMother.random());

        ItemDetails response = service.createItem(ItemDetailMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @Test
    public void shouldDeleteItem() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(ItemEntityMother.random()));
        when(mapper.mapToDto(any(ItemEntity.class)))
                .thenReturn(ItemDetailMother.random());

        service.deleteItem(LongMother.random());

        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    public void shouldModifyItem() {
        ItemDetails request = ItemDetailMother.random();

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(ItemEntityMother.random()));
        when(mapper.mapToDto(any(ItemEntity.class)))
                .thenReturn(ItemDetailMother.random());
        when(mapper.mapToEntityUpdate(any(ItemDetails.class), any(ItemDetails.class)))
                .thenReturn(ItemDetailMother.random());
        when(mapper.mapToEntity(any(ItemDetails.class)))
                .thenReturn(ItemEntityMother.random());
        when(repository.save(any(ItemEntity.class)))
                .thenReturn(ItemEntityMother.random());

        ItemDetails response = service.modifyItem(request.getId(), request);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @Test
    public void shouldModifyItemConflictException() {
        Assertions.assertThrows(ConflictException.class,
                () -> service.modifyItem(LongMother.random(), ItemDetailMother.random())
        );
    }

    @Test
    public void shouldReadItem() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(ItemEntityMother.random()));
        when(mapper.mapToDto(any(ItemEntity.class)))
                .thenReturn(ItemDetailMother.random());

        ItemDetails response = service.readItem(LongMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @Test
    public void shouldReadItemNotFoundException() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.readItem(LongMother.random())
        );
    }

    @Test
    public void shouldReadAllItems() {
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(ItemEntityMother.random(), ItemEntityMother.random())));
        when(mapper.mapToDto(any(ItemEntity.class)))
                .thenReturn(ItemDetailMother.random());

        Page<ItemDetails> response = service.readAllItems(Pageable.unpaged());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(2, response.getTotalElements()),
                () -> Assertions.assertNotNull(response.getContent()),
                () -> Assertions.assertNotNull(response.getContent().get(0).getId())
        );
    }
}
