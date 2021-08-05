package com.example.test;

import com.example.test.dto.CreateGameDto;
import com.example.test.dto.EndGameDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestApplication.class})
@WebAppConfiguration
class TestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void exampleOne() throws Exception {
        List<String> testOne = new ArrayList<>(Arrays.asList("fish", "horse", "egg", "goose", "eagle"));
        List<String> resultTestOne = new ArrayList<>(Arrays.asList("fish", "horse", "egg", "goose", "eagle"));
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateGameDto(testOne))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new EndGameDto(resultTestOne))));
    }

    @Test
    public void exampleTwo() throws Exception {
        List<String> testTwo = new ArrayList<>(Arrays.asList("fish", "horse", "shake", "goose", "eagle"));
        List<String> resultTestTwo = new ArrayList<>(Arrays.asList("fish", "horse"));
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateGameDto(testTwo))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new EndGameDto(resultTestTwo))));
    }

    @Test
    public void exampleThree() throws Exception {
        List<String> testThree = new ArrayList<>(Arrays.asList("fish", "horse", "", "goose", "eagle"));
        List<String> resultTestThree = new ArrayList<>(Arrays.asList("fish", "horse"));
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateGameDto(testThree))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new EndGameDto(resultTestThree))));
    }

    @Test
    public void exampleFour() throws Exception {
        List<String> testFour = new ArrayList<>(Arrays.asList("", "horse", "", "goose", "eagle"));
        List<String> resultTestFour = new ArrayList<>();
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateGameDto(testFour))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new EndGameDto(resultTestFour))));
    }
}
