package org.example.libis.model;



import java.time.LocalDate;

public class Loan {

    private int loanId;
    private int memberId;
    private int bookId;
    private LocalDate borrowedOn;
    private LocalDate returnedOn;

    public Loan() {
    }

    public Loan(int loanId, int memberId, int bookId,
                LocalDate borrowedOn, LocalDate returnedOn) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowedOn = borrowedOn;
        this.returnedOn = returnedOn;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowedOn() {
        return borrowedOn;
    }

    public void setBorrowedOn(LocalDate borrowedOn) {
        this.borrowedOn = borrowedOn;
    }

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", borrowedOn=" + borrowedOn +
                ", returnedOn=" + returnedOn +
                '}';
    }
}
