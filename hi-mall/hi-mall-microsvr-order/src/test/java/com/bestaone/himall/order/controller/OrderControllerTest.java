package com.bestaone.himall.order.controller;

import com.bestaone.himall.api.order.dto.OrderDto;
import com.bestaone.himall.order.OrderApplication;
import com.bestaone.himall.order.enums.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mockMvc.perform(
                get("/api/order")
                        .param("pageNum", "1")
                        .param("pageSize", "10")
                        .param("status", OrderStatus.PAID.name())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/api/order/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        String result = mockMvc.perform(get("/api/order/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        OrderDto dto = new OrderDto().setTitle("iphone x 1").setTotalAmount(10001F);
        String result = mockMvc.perform(post("/api/order").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateFail() throws Exception {
        OrderDto dto = new OrderDto();
        String result = mockMvc.perform(post("/api/order").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(50000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        OrderDto dto = new OrderDto().setTitle("iphone x 2").setTotalAmount(10001F);
        String result = mockMvc.perform(put("/api/order/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mockMvc.perform(delete("/api/order/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

}
