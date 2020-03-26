package com.example.library_fullstack.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateLoanForm {
    @NotBlank(message = "Enter start of loan date")
    private String startDate;
    @NotBlank(message = "Enter end of loan date")
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
