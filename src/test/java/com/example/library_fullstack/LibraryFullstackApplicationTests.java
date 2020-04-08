package com.example.library_fullstack;

import com.example.library_fullstack.controller.AdminController;
import com.example.library_fullstack.controller.AppUserController;
import com.example.library_fullstack.controller.IndexController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryFullstackApplicationTests {


    @Autowired private AdminController adminController;
    @Autowired private AppUserController appUserController;
    @Autowired private IndexController indexController;

    @Test
    void contextLoads() {
        assertNotNull(adminController);
        assertNotNull(appUserController);
        assertNotNull(indexController);
    }

}
