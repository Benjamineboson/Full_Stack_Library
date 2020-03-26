package com.example.library_fullstack.controller;

import com.example.library_fullstack.data.AppUserRepository;
import com.example.library_fullstack.data.LibraryBookRepository;
import com.example.library_fullstack.data.LoanRepository;
import com.example.library_fullstack.dto.CreateLoanForm;
import com.example.library_fullstack.entity.AppUser;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
public class AppUserController {


    private LibraryBookRepository libraryBookRepository;
    private AppUserRepository appUserRepository;
    private LoanRepository loanRepository;

    @Autowired
    public AppUserController(LibraryBookRepository libraryBookRepository, AppUserRepository appUserRepository, LoanRepository loanRepository) {
        this.libraryBookRepository = libraryBookRepository;
        this.appUserRepository = appUserRepository;
        this.loanRepository = loanRepository;
    }

    @GetMapping("/books")
    public String getBookView(Model model){
        List<LibraryBook> libraryBookList = libraryBookRepository.findAll();
        model.addAttribute("bookList",libraryBookList);
        return "books-view";
    }

    @GetMapping("/create/loan/{libraryBookId}")
    public String getCreateLoanForm(Model model, @PathVariable("libraryBookId") int libraryBookId){
        model.addAttribute("form",new CreateLoanForm());
        model.addAttribute("libraryBookId",libraryBookId);
        LibraryBook book = libraryBookRepository.findById(libraryBookId).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("book",book);
        return "create-loan";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/create/loan/process")
    public String processCreateLoanForm(@Valid @ModelAttribute("form") CreateLoanForm form){
        AppUser user = appUserRepository.findByEmailIgnoreCase(form.getAppUserEmail()).orElseThrow(IllegalArgumentException::new);
        LibraryBook book = libraryBookRepository.findById(Integer.valueOf(form.getLibraryBookId())).orElseThrow(IllegalAccessError::new);
        book.setAvailable(false);
        Loan loan = new Loan(LocalDate.parse(form.getStartDate()),LocalDate.parse(form.getEndDate()), user,book);
        user.addLoan(loan);
        loanRepository.save(loan);
        return "redirect:/index";
    }
}
