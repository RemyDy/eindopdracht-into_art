package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.config.ConfigSpringBootTest;
import com.eindopdracht_into_art.services.interfaces.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class HelloControllerTest extends ConfigSpringBootTest {

    private final String teacher = "World!, Teacher, Hello Teacher!";
    private final String richard = "World!, Richard, Hello Richard!";
    // above is used for parameterizedTest
    // order is -> default value, param given, expected result

    @MockBean
    private HelloService service;

    @Test
    @DisplayName("Test if mock is created")
    void test_mock_hello() {
        assertNotNull(mockMvc);
    }

    @Test
    @DisplayName("Test returns standard greeting, when no param values are given.")
    void test_hello() throws Exception {

        when(service.getGreeting(anyString())).thenReturn("Hello World!");

        this.mockMvc
                .perform(get("/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World!")))
                .andDo(print());
    }

    @CsvSource({teacher, richard})
    @ParameterizedTest
    @DisplayName("Test returns custom greetings. When param values are given.")
    void test_hello_params(
            String defaultValue, String name, String expectedResult
    ) throws Exception {

        when(service.getGreeting(defaultValue)).thenReturn(expectedResult);

        this.mockMvc
                .perform(get("/hello").queryParam(name, name))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)))
                .andDo(print());
    }

}


