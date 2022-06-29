package com.technical.sprinter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.IOException;
import java.net.URL;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class SprinterTestIT {

    private static final String HTTP_BASIC_USER = "sprinter";
    private static final String HTTP_BASIC_PASSWORD = "sprinter";

    protected ObjectMapper objectMapper;

    @BeforeEach
    public void initMapper() {
        objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
    }

    protected String getStringContentFile(String fileRoute) throws IOException {
        Object json = objectMapper.readValue(new URL(fileRoute), Object.class);
        return objectMapper.writeValueAsString(json);
    }

    protected <T> T getObjectResponse(String response, Class<T> clazz) throws IOException {
        return objectMapper.readValue(response, clazz);
    }

    protected RequestPostProcessor httpBasic() {
        return SecurityMockMvcRequestPostProcessors.httpBasic(HTTP_BASIC_USER, HTTP_BASIC_PASSWORD);
    }
}
