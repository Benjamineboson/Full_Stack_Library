package com.example.library_fullstack.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class LibraryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int libraryBookId;
    private String title;
    private int maxLoanDays;
    private boolean isAvailable;
    private boolean isReserved;

    public LibraryBook(String title, int maxLoanDays) {
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.isAvailable = true;
        this.isReserved = false;
    }

    public LibraryBook(){}

    public int getLibraryBookId() {
        return libraryBookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryBook that = (LibraryBook) o;
        return libraryBookId == that.libraryBookId &&
                maxLoanDays == that.maxLoanDays &&
                isAvailable == that.isAvailable &&
                isReserved == that.isReserved &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryBookId, title, maxLoanDays, isAvailable, isReserved);
    }

    @Override
    public String toString() {
        return "LibraryBook{" +
                "libraryBookId=" + libraryBookId +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                ", isAvailable=" + isAvailable +
                ", isReserved=" + isReserved +
                '}';
    }
}
