package com.example.library_fullstack.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void non_authenticated_user_accessing_users_should_redirect_to_login() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void user_accessing_users_should_return_status_403 () throws Exception{
        mockMvc.perform(get("/users").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void admin_accessing_users_should_return_users_view () throws Exception{
        mockMvc.perform(get("/users").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(view().name("users-view"));
    }

    @Test
    public void get_create_user() throws Exception{
        mockMvc.perform(get("/create/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-user"))
                .andExpect(model().attributeExists("form"));
    }

    @Test
    public void create_user_process_given_correct_input_parameters_should_succeed() throws Exception{
        mockMvc.perform(post("/create/user/process")
                .param("firstName","Benjamin")
                .param("lastName","Boson")
                .param("email","Ben@test.com")
                .param("password","1a1b1c1d")
                .param("passwordConfirm","1a1b1c1d"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("index"));
    }

    @Test
    public void create_user_process_given_bad_input_parameters_should_fail() throws Exception{
        mockMvc.perform(post("/create/user/process")
                .param("firstName","B")
                .param("lastName","B")
                .param("email","@Ben@test.com")
                .param("password","1a1b1c1d")
                .param("passwordConfirm","1as1b1c1d"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("form"))
                .andExpect(model().attributeHasFieldErrors("form","firstName","lastName","email","passwordConfirm"));
    }

    @Test
    public void admin_accessing_create_book_should_return_create_book() throws Exception{
        mockMvc.perform(get("/create/book").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(view().name("create-book"));
    }

    @Test
    public void user_accessing_create_book_should_return_status_403() throws Exception{
        mockMvc.perform(get("/create/book").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void non_authenticated_user_accessing_create_book_should_redirect_to_login() throws Exception{
        mockMvc.perform(get("/create/book"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void create_book_process_given_correct_input_parameters_should_succeed() throws Exception{
        mockMvc.perform(post("/create/book/process").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER")))
                .param("title","War and Peace")
                .param("maxLoanDays","90"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("books-view"));
    }

    @Test
    public void create_book_process_given_bad_input_parameters_should_fail() throws Exception{
        mockMvc.perform(post("/create/book/process").with(user("test@test.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER")))
                .param("title","W")
                .param("maxLoanDays","900"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("form"))
                .andExpect(model().attributeHasFieldErrors("form","title","maxLoanDays"));
    }
}

