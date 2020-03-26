package com.example.library_fullstack.service;

import com.example.library_fullstack.entity.AppUser;

import java.time.LocalDate;

public interface AppUserService {
    AppUser registerAppUser(String firstName, String lastName, String email, String password, LocalDate regDate, boolean isAdmin);
}
