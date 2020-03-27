package com.example.library_fullstack.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateLoanForm {
    @NotBlank(message = "Please enter loan date")
    private String startDate;
    @NotBlank(message = "Please enter return date")
    private String endDate;
    private String appUserEmail;
    private String libraryBookId;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAppUserEmail() {
        return appUserEmail;
    }

    public void setAppUserEmail(String appUserEmail) {
        this.appUserEmail = appUserEmail;
    }

    public String getLibraryBookId() {
        return libraryBookId;
    }

    public void setLibraryBookId(String libraryBookId) {
        this.libraryBookId = libraryBookId;
    }
}
