package com.example.booksapp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
     void ShouldReturnMessageForAdmin() throws Exception {
            //given
            //when
        this.mockMvc.perform(get("/admin").with(httpBasic("admin","admin"))).andDo(print()).andExpect(status().is(200))
                .andExpect(content().string(containsString("Secret message for admin")));
            //then
    }

    @Test
    void ShouldReturnMessageForUser() throws Exception {
        //given
        //when
        this.mockMvc.perform(get("/t").with(httpBasic("user","user"))).andDo(print()).andExpect(status().is(200))
                .andExpect(content().string(containsString("Test seems to be working")));
        //then
    }

}
