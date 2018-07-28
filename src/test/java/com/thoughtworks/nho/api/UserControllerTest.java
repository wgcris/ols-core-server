package com.thoughtworks.nho.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.nho.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Test
    void should_create_user() throws Exception {
        loginWithUser("future_star");
        User user = User.builder().name("test").password("123456789").build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.username").value("test"));
    }

    @Test
    void should_existed_user() throws Exception {
        User user = User.builder().name("admin").password("123456789").build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code").value("-1"))
                .andExpect(jsonPath("$.message").value("User already exists."));
    }

    @Test
    void should_return_name_error() throws Exception {
        User user = User.builder().name("admin23132").password("123456").build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code").value("-1"))
                .andExpect(jsonPath("$.message").value("必须是1-10位英文字符，不能有空格"));
    }

    @Test
    void should_return_pwd_error() throws Exception {
        User user = User.builder().name("test").password("123456").build();
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.code").value("-1"))
                .andExpect(jsonPath("$.message").value("必须是大于8个的数字，不能有空格"));
    }
}