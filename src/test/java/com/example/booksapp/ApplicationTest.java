package com.example.booksapp;



import com.example.booksapp.Model.Role;
import com.example.booksapp.Model.User;
import com.example.booksapp.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

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

    @Test
    void shouldThrowError403ForUser() throws Exception{
        //given
        //when
            this.mockMvc.perform(get("/admin").with(httpBasic("user","user"))).andDo(print()).andExpect(status().is(403));
        //then
    }

    @Test

    void shouldAddUserToDatabase(){
        //given
        User testUser=new User("123123","TestUsername","TestPassword", Role.USER, LocalDateTime.now().toString(),true);
        //when
        userService.addUser(testUser);
        assertEquals(userService.findByUsername("TestUsername"),testUser);
        //then
        userService.deleteUserByUsername("TestUsername");//deleting user to clean db
    }

    @Test

    void shouldGetAllUsers() throws Exception {
        //given
        User testUser=new User("0001","TestUsername","TestPassword", Role.USER, LocalDateTime.now().toString(),true);
        User testUser2=new User("0002","TestUsername2","TestPassword", Role.USER, LocalDateTime.now().toString(),true);
        userService.addUser(testUser);
        userService.addUser(testUser2);
        //when
        this.mockMvc.perform(get("/users").with(httpBasic("admin","admin")))
                .andDo(print()).andExpect(status().is(200));
        //then
        userService.deleteUserByUsername("TestUsername");
        userService.deleteUserByUsername("TestUsername2");

    }


}
