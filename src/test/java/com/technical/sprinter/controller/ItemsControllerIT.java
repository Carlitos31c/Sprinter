package com.technical.sprinter.controller;

import com.technical.sprinter.SprinterTestIT;
import com.technical.sprinter.model.ItemDetails;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemsControllerIT extends SprinterTestIT {

    private static final String ITEMS_URL = "/items";
    private static final String ITEMS_BY_ID_URL = "/items/1";
    private static final String ITEMS_BY_ID_ERROR_URL = "/items/x";

    private static final String ITEM_REQUEST = "file:src/test/resources/request/item.json";
    private static final String ITEM_ERROR_REQUEST = "file:src/test/resources/request/item-error.json";

    @Autowired
    protected MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void shouldCreateItemDetailsResponse201() {
        String requestFile = getStringContentFile(ITEM_REQUEST);

        String responseMvc = this.mockMvc.perform(post(ITEMS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(httpBasic())
                .content(requestFile)
            ).andExpect(status().is2xxSuccessful())
            .andReturn().getResponse().getContentAsString();

        ItemDetails request = getObjectResponse(requestFile, ItemDetails.class);
        ItemDetails response = getObjectResponse(responseMvc, ItemDetails.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getId()),
                () -> Assertions.assertEquals(request.getName(), response.getName()),
                () -> Assertions.assertEquals(request.getSport(), response.getSport())
        );
    }

    @SneakyThrows
    @Test
    public void shouldCreateItemDetailsResponse4XX() {
        this.mockMvc.perform(post(ITEMS_URL)
                    .with(httpBasic())
                ).andExpect(status().is4xxClientError())
                .andReturn().getResponse();
    }

    @SneakyThrows
    @Test
    public void shouldCreateItemDetailsResponse5XX() {
        String requestFile = getStringContentFile(ITEM_ERROR_REQUEST);

        this.mockMvc.perform(post(ITEMS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(httpBasic())
                .content(requestFile)
            ).andExpect(status().is5xxServerError())
            .andReturn().getResponse();
    }

    @SneakyThrows
    @Test
    public void shouldReadItemDetailsResponse200() {
        String responseMvc = this.mockMvc.perform(get(ITEMS_BY_ID_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(httpBasic())
                ).andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        ItemDetails response = getObjectResponse(responseMvc, ItemDetails.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertNotNull(response.getId())
        );
    }

    @SneakyThrows
    @Test
    public void shouldReadItemDetailsResponse4XX() {
        this.mockMvc.perform(get(ITEMS_BY_ID_ERROR_URL)
                .with(httpBasic())
            ).andExpect(status().is4xxClientError())
            .andReturn().getResponse();
    }

}
