package com.example.library_fullstack.data;

import com.example.library_fullstack.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Integer> {
    Optional<AppUser> findByEmailIgnoreCase(String email);
}
