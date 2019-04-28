package com.ynz.event.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ynz.event.domainvalue.Action;
import com.ynz.event.domainvalue.Source;
import com.ynz.event.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EventControllerTest {

    @Autowired
    private MockMvc mvc;

    private Event faked;

    @Before
    public void setup() {
        faked = Event.builder()
                .action(Action.ADD.toString())
                .source(Source.B.toString())
                .build();  //.createdAt(LocalDateTime.now())
    }

    @Test
    public void testPostEventSuccessfully() throws Exception {
        mvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convert(faked)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.source", is(Source.B.toString())));
    }

    @Test
    public void testBadRequest() throws Exception {
        mvc.perform(post("/events/create").contentType(MediaType.APPLICATION_JSON).content(convert(faked)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testFindEventById_NotFound() throws Exception {
        String id = UUID.randomUUID().toString();
        mvc.perform(get("/events/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convert(final Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}