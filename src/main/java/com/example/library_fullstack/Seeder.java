package com.example.library_fullstack;

import com.example.library_fullstack.data.AppRoleRepository;
import com.example.library_fullstack.data.AppUserRepository;
import com.example.library_fullstack.data.LibraryBookRepository;
import com.example.library_fullstack.data.LoanRepository;
import com.example.library_fullstack.entity.AppRole;
import com.example.library_fullstack.entity.AppUser;
import com.example.library_fullstack.entity.LibraryBook;
import com.example.library_fullstack.entity.Loan;
import com.example.library_fullstack.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class Seeder implements CommandLineRunner {

    private LibraryBookRepository libraryBookRepository;
    private AppUserRepository appUserRepository;
    private LoanRepository loanRepository;
    private AppUserService appUserService;
    AppRoleRepository appRoleRepository;

    @Autowired
    public Seeder(LibraryBookRepository libraryBookRepository, AppUserRepository appUserRepository, LoanRepository loanRepository, AppUserService appUserService, AppRoleRepository appRoleRepository) {
        this.libraryBookRepository = libraryBookRepository;
        this.appUserRepository = appUserRepository;
        this.loanRepository = loanRepository;
        this.appUserService = appUserService;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        appRoleRepository.save(new AppRole("ADMIN"));
        appRoleRepository.save(new AppRole("USER"));
        libraryBookRepository.saveAll(
                Arrays.asList(new LibraryBook("War and Peace",90),
                        new LibraryBook("A Game of Thrones",150),
                        new LibraryBook("Winds of Winter",60),
                        new LibraryBook("The Winter Soldier",150),
                        (new LibraryBook("A Clash of Kings",90)
)
        ));
        appUserService.registerAppUser("Benjamin","Boson","BenjaminEBoson@Gmail.com","1a1b1c1d", LocalDate.now(),true);
    }


}
