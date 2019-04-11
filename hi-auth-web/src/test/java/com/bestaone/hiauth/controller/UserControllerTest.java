package com.bestaone.hiauth.controller;

import com.bestaone.hiauth.HiAuthApplication;
import com.bestaone.hiauth.api.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HiAuthApplication.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Resource
    private ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String result = mvc.perform(
                get("/api/user")
                        .param("pageNum", "1")
                        .param("pageSize", "10")
                        .param("name", "1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {
        String result = mvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        String result = mvc.perform(get("/api/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        UserDto dto = new UserDto().setName("zhangsan").setUsername("test").setPassword("123").setGender("MALE");
        String result = mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenCreateFail() throws Exception {
        UserDto dto = new UserDto();
        String result = mvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(50000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        UserDto dto = new UserDto().setName("zhangsan").setUsername("test").setPassword("123").setGender("MALE");
        String result = mvc.perform(put("/api/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mvc.perform(delete("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(10000))
                .andReturn().getResponse().getContentAsString();
        log.debug("返回结果：{}", result);
    }

}
