package org.example.libis.dao;


import org.example.libis.model.Loan;

import java.util.List;

public interface LoanDAO {

    int create(int memberId, int bookId);

    int countOpenByMember(int memberId);
    int hasOpenLoan(int bookId);


    int markRetuned(int bookId);

    List<Loan> findOpenByMember(int memberId);

    List<Loan> findHistoryByMember(int memberId);
}
