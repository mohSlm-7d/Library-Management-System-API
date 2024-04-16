package com.library.api.bean;

import java.time.LocalDate;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Component()
@Scope(value="prototype")
@IdClass(BorrowingRecordId.class)
public class BorrowingRecord {	
//	@EmbeddedId
//	private BorrowingRecordId borrowingRecordId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "book_id")
//	@ForeignKey(name = "fk_book_id")
	private Book borrowedBook;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "patron_id")
//	@ForeignKey(name = "fk_patron_id")
	private Patron borrowingPatron;
	
	
	

	@Column(nullable = false)
	private LocalDate borrowingDate;
	
	@Column(columnDefinition = "date default null")
	private LocalDate returnDate;

	
	
	
	public Book getBorrowedBook() {
		return borrowedBook;
	}

	public void setBorrowedBook(Book borrowedBook) {
		this.borrowedBook = borrowedBook;
	}

	public Patron getBorrowingPatron() {
		return borrowingPatron;
	}

	public void setBorrowingPatron(Patron borrowingPatron) {
		this.borrowingPatron = borrowingPatron;
	}

//	public BorrowingRecordId getBorrowingRecordId() {
//		return borrowingRecordId;
//	}
//
//	public void setBorrowingRecordId(BorrowingRecordId borrowingRecordId) {
//		this.borrowingRecordId = borrowingRecordId;
//	}
	
	
	

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	@Override
	public String toString() {
		return "BorrowingRecord [borrowedBook=" + borrowedBook + ", borrowingPatron=" + borrowingPatron
				+ ", borrowingDate=" + borrowingDate + ", returnDate=" + returnDate + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		BorrowingRecord cloneCopy = new BorrowingRecord();
		cloneCopy.setBorrowingPatron((Patron)this.borrowingPatron.clone());
		cloneCopy.setBorrowedBook((Book)this.borrowedBook.clone());
		cloneCopy.setBorrowingDate(this.borrowingDate);
		cloneCopy.setReturnDate(this.returnDate);
		
		return cloneCopy;
	}
	
	
	
//	@Override
//	public String toString() {
//		return "BorrowingRecord [borrowingRecordId=" + borrowingRecordId + ", borrowingDate=" + borrowingDate
//				+ ", returnDate=" + returnDate + "]";
//	}
//
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		BorrowingRecord cloneCopy = new BorrowingRecord();
//		cloneCopy.setBorrowingRecordId(this.borrowingRecordId);
//		cloneCopy.setBorrowingDate(this.borrowingDate);
//		cloneCopy.setReturnDate(this.returnDate);
//		
//		return cloneCopy;
//	}
	
}
