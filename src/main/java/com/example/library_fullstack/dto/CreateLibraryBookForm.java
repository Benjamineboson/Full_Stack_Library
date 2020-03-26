package com.example.library_fullstack.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateLibraryBookForm {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title needs to be between 2-255 characters")
    private String title;
    private String maxLoanDays;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(String maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }
}
