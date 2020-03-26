package com.example.library_fullstack.data;

import com.example.library_fullstack.entity.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan,Integer> {
}
