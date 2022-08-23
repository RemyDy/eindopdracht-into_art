package com.example.eindopdracht_into_art.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Should return string: Hello World")
    void test_hello() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(print());
    }

    @Test
    @DisplayName("Should return string: Hello Teacher")
    void test_hello_param() throws Exception {
        this.mockMvc
                .perform(get("/").queryParam("name", "Teacher"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Teacher")))
                .andDo(print());
    }

    @Test
    @DisplayName("Should return statuscode: 404")
    void test_wrong_endpoint() throws Exception {
        this.mockMvc
                .perform(get("/hello"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
