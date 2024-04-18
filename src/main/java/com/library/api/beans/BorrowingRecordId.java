package com.library.api.beans;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@Embeddable
public class BorrowingRecordId implements Serializable{
//	@Id
//	@Embedded
//	@ManyToOne(fetch = FetchType.LAZY)
//	private int bookId;
	
//	@Id
//	@Embedded
//	@ManyToOne(fetch = FetchType.LAZY)
//	private int patronId;

	
	private Book borrowedBook;

	private Patron borrowingPatron;

	public BorrowingRecordId() {
		super();
	}
	
	
	
	
	public BorrowingRecordId(Book borrowedBook, Patron borrowingPatron) {
		super();
		this.borrowedBook = borrowedBook;
		this.borrowingPatron = borrowingPatron;
	}

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

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
	
	
	
	
}
