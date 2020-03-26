package com.example.library_fullstack.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CreateLoanForm {
    @NotBlank(message = "Enter start of loan date")
    private LocalDate startDate;
    @NotBlank(message = "Enter end of loan date")
    private LocalDate endDate;

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
}
