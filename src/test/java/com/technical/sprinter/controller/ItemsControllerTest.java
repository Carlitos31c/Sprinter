package com.technical.sprinter.controller;

import com.technical.sprinter.domain.ItemDetailMother;
import com.technical.sprinter.domain.LongMother;
import com.technical.sprinter.model.ItemDetails;
import com.technical.sprinter.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemsControllerTest {

    @Mock
    private ItemService service;

    @InjectMocks
    private ItemsController controller;

    @Test
    public void shouldCreateItem() {
        when(service.createItem(any(ItemDetails.class)))
                .thenReturn(ItemDetailMother.random());

        ResponseEntity<ItemDetails> response = controller.createItemDetails(ItemDetailMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getBody()),
                () -> Assertions.assertNotNull(response.getBody().getId()),
                () -> Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }

    @Test
    public void shouldDeleteItem() {
        ResponseEntity<Void> response = controller.deleteItemDetails(LongMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode())
        );
    }

    @Test
    public void shouldModifyItem() {
        when(service.modifyItem(anyLong(), any(ItemDetails.class)))
                .thenReturn(ItemDetailMother.random());

        ResponseEntity<ItemDetails> response = controller.modifyItemDetails(LongMother.random(), ItemDetailMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getBody()),
                () -> Assertions.assertNotNull(response.getBody().getId()),
                () -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    public void shouldReadItem() {
        when(service.readItem(anyLong()))
                .thenReturn(ItemDetailMother.random());

        ResponseEntity<ItemDetails> response = controller.readItemDetails(LongMother.random());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getBody()),
                () -> Assertions.assertNotNull(response.getBody().getId()),
                () -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    public void shouldReadAllItems() {
        when(service.readAllItems(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(ItemDetailMother.random(),ItemDetailMother.random())));

        ResponseEntity<Page<ItemDetails>> response = controller.readAllItemDetails(Pageable.unpaged());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getBody()),
                () -> Assertions.assertEquals(2, response.getBody().getTotalElements()),
                () -> Assertions.assertNotNull(response.getBody().getContent()),
                () -> Assertions.assertNotNull(response.getBody().getContent().get(0).getId()),
                () -> Assertions.assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }
}
