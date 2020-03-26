package com.example.library_fullstack;

import com.example.library_fullstack.data.AppUserRepository;
import com.example.library_fullstack.data.LibraryBookRepository;
import com.example.library_fullstack.data.LoanRepository;
import com.example.library_fullstack.entity.AppUser;
import com.example.library_fullstack.entity.LibraryBook;
import com.example.library_fullstack.entity.Loan;
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

    @Autowired
    public Seeder(LibraryBookRepository libraryBookRepository, AppUserRepository appUserRepository, LoanRepository loanRepository) {
        this.libraryBookRepository = libraryBookRepository;
        this.appUserRepository = appUserRepository;
        this.loanRepository = loanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser user = new AppUser("Benjamin","Boson","BenjaminEBoson@Gmail.com","1a1b1c1d", LocalDate.now());
        LibraryBook book = new LibraryBook("Harry Potter",14);
        libraryBookRepository.saveAll(
                Arrays.asList(new LibraryBook("Harry Potter",14),
                        new LibraryBook("Harry Potter2",14),
                        new LibraryBook("Harry Potter3",114),
                        new LibraryBook("Harry Potter4",124),
                        (new LibraryBook("Harry Potter5",164)
)
        ));
        Loan loan = new Loan(LocalDate.now(),LocalDate.of(2020,04,15),user,book);
        user = appUserRepository.save(user);
        loan = loanRepository.save(loan);
    }


}
