package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.controller.repositories.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    //region Test Setup
    private static final String WORLD = "World!, World!, Hello World! ";
    private static final String TEACHER = "World!, Teacher, Hello Teacher!";
    private static final String RICHARD = "World!, Richard, Hello Richard!";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HelloService service;
    //endregion

    @CsvSource({WORLD})
    @ParameterizedTest
    @DisplayName("Test returns standard greeting, when no param is given.")
    void test_hello_whenNoParamGiven_correctDefaultUsed(
            String defaultValue, String newValue, String expectedResult) throws Exception {

        when(service.getGreeting(defaultValue)).thenReturn(expectedResult);

        this.mockMvc
                .perform(get("/").queryParam(newValue, ""))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)))
                .andDo(print());
    }

    @CsvSource({TEACHER, RICHARD,})
    @ParameterizedTest
    @DisplayName("Test returns custom greetings. When param values are given.")
    void test_hello_whenParamsGiven(
            String defaultValue, String newValue, String expectedResult) throws Exception {

        when(service.getGreeting(defaultValue)).thenReturn(expectedResult);

        this.mockMvc
                .perform(get("/").queryParam(newValue, newValue))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)))
                .andDo(print());
    }

    @Test
    @DisplayName("Test return status code 404. When wrong endpoint is used.")
    void test_hello_wrongEndpoint() throws Exception {
        this.mockMvc
                .perform(get("/hello"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
