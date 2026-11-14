package org.example.libis.service;

import org.example.libis.dao.BookDAO;
import org.example.libis.dao.BookDAOImpl;
import org.example.libis.dao.LoanDAO;
import org.example.libis.dao.LoanDAOImpl;
import org.example.libis.exeception.LoanException;
import org.example.libis.model.Book;

public class LoanServiceImpl implements LoanInterface{

    private final LoanDAO loanDAO = new LoanDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();
    public static final int MAX_LOANS = 3;


    @Override
    public void borrowBook(int memberId, int bookId) throws LoanException {

        Book book = bookDAO.findById(bookId);

        if(book == null){
            throw new LoanException(
                    "Book does not exist."
            );
        }

        if(loanDAO.hasOpenLoan(bookId)){
            throw new LoanException(
                    book.getTitle() +
                            " cannot be borrowed because it is already on loan."
            );
        }
        if(loanDAO.countOpenByMember(memberId) >= MAX_LOANS){
            throw new LoanException(
                    "You already have "
                            + MAX_LOANS
                            + " books on loan, the maximum allowed."
            );
        }

        int result = loanDAO.create(memberId, bookId);

        if(result == 0){
            throw new LoanException(
                    "Borrowing failed."
            );
        }

        loanDAO.create(memberId,bookId);
    }


        @Override
        public void returnBook(int memberId, int bookId)
            throws LoanException {

            boolean loanBelongsToMember =
                    loanDAO.findOpenByMember(memberId)
                            .stream()
                            .anyMatch(
                                    loan -> loan.getBookId() == bookId
                            );

            if(!loanBelongsToMember){
                throw new LoanException(
                        "You can only return books currently borrowed by you."
                );
            }

            int result = loanDAO.markRetuned(bookId);

            if(result == 0){
                throw new LoanException(
                        "Return failed."
                );
            }
        }
    }

