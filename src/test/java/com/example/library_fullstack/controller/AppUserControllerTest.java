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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AppUserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void non_authorized_user_accessing_loans_view_should_return_access_denied() throws Exception{
        mvc.perform(get("/loans/{email}"," "))
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"));
    }

    @Test
    public void authorized_user_accessing_loans_view_should_return_loans_view() throws Exception{
        mvc.perform(get("/loans/{email}","BenjaminEBoson@gmail.com").with(user("BenjaminEBoson@gmail.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(view().name("loans-view"));
    }

    @Test
    public void authorized_user_accessing_other_users_loans_view_should_return_access_denied() throws Exception{
        mvc.perform(get("/loans/{email}","Tester@gmail.com").with(user("BenjaminEBoson@gmail.com")
                .authorities(new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"));
    }

    @Test
    public void non_authenticated_user_accessing_books_should_redirect_to_login() throws Exception{
        mvc.perform(get("/books"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void authenticated_user_accessing_books_with_no_search_param_should_return_books_view_with_all_books() throws Exception{
        mvc.perform(get("/books").with(user("BenjaminEBoson@gmail.com")
                .authorities(new SimpleGrantedAuthority("ADMIN"),new SimpleGrantedAuthority("USER"))))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bookList"));
    }
}
