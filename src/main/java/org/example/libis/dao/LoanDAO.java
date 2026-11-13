package org.example.libis.dao;


public interface LoanDAO {

    int create(int memberId, int bookId);

    int countOpenByMember(int memberId);
    int hasOpenLoan(int bookId);


}
