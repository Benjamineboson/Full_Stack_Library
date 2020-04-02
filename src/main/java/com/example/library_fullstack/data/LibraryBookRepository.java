package com.example.library_fullstack.data;

import com.example.library_fullstack.entity.LibraryBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryBookRepository extends CrudRepository<LibraryBook,Integer> {
    List<LibraryBook> findAll();
    List<LibraryBook> findByTitleContainsIgnoreCase(String title);
}
