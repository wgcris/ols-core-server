package com.thoughtworks.nho.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.nho.cofiguration.security.LoginRequestUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationControllerTest extends BaseControllerTest {
    @Test
    @Disabled
    void should_login() throws Exception {
        loginWithUser("future_star");
        LoginRequestUser user = LoginRequestUser.builder().username("test").password("123456").build();

        mockMvc.perform(post("/api/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("0"))
                .andExpect(jsonPath("$.data.username").value(user.getUsername()))
                .andExpect(jsonPath("$.data.password").value(user.getPassword()));
    }
}