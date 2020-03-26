package com.example.library_fullstack.controller;

import com.example.library_fullstack.data.LibraryBookRepository;
import com.example.library_fullstack.dto.CreateLoanForm;
import com.example.library_fullstack.entity.LibraryBook;
import com.example.library_fullstack.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class AppUserController {


    private LibraryBookRepository libraryBookRepository;

    @Autowired
    public AppUserController(LibraryBookRepository libraryBookRepository) {
        this.libraryBookRepository = libraryBookRepository;
    }

    @GetMapping("/books")
    public String getBookView(Model model){
        List<LibraryBook> libraryBookList = libraryBookRepository.findAll();
        model.addAttribute("bookList",libraryBookList);
        return "books-view";
    }

    @GetMapping("/create/loan")
    public String getCreateLoanForm(Model model){
        model.addAttribute("form",new CreateLoanForm());
        return "create-loan";
    }

    @PostMapping("/create/loan/process")
    public String processCreateLoanForm(@Valid @ModelAttribute("form") CreateLoanForm form, BindingResult bindingResult,
                                        @PathVariable(name = "appUserId") int appUserId,
                                        @PathVariable(name = "libraryBookId") int libraryBookId){
        Loan
    }
}
