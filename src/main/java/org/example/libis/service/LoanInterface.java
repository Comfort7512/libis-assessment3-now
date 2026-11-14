package org.example.libis.service;

import org.example.libis.exeception.LoanException;

public interface LoanInterface {
    void borrowBook(int memberId, int bookId) throws LoanException;

    void returnBook(int memberId, int bookId) throws LoanException;
}
