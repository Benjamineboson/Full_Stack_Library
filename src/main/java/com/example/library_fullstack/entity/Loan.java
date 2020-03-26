package com.example.library_fullstack.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH}
    )
    private AppUser appUser;
    @OneToOne(
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private LibraryBook libraryBook;

    public Loan(LocalDate startDate, LocalDate endDate, AppUser appUser, LibraryBook libraryBook) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.appUser = appUser;
        this.libraryBook = libraryBook;
    }

    public Loan(){}

    public int getLoanId() {
        return loanId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public LibraryBook getLibraryBook() {
        return libraryBook;
    }

    public void setLibraryBook(LibraryBook libraryBook) {
        this.libraryBook = libraryBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId &&
                Objects.equals(startDate, loan.startDate) &&
                Objects.equals(endDate, loan.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
