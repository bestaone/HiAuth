package com.bestaone.aiwan.auth.web.controller;

import com.bestaone.aiwan.auth.api.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

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
                get("/user")
                        .param("pageNum", "1")
                        .param("pageSize", "10")
                        .param("name", "1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mockMvc.perform(get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        String result = mockMvc.perform(get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        UserDto dto = new UserDto().setName("zhangsan").setUsername("test").setPassword("123");
        String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateFail() throws Exception {
        UserDto dto = new UserDto();
        String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(50000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        UserDto dto = new UserDto().setName("zhangsan").setUsername("test").setPassword("123");
        String result = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mockMvc.perform(delete("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        logger.debug("返回结果：{}", result);
    }

}
