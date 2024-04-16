package com.library.api.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"listOfBorrowingRecords"})
@Entity
@Component()
@Scope(value="prototype")
//@Embeddable
public class Book {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
	private int bookId;
	
	@Column(nullable = false)
	private String bookTitle;
	
	@Column(nullable = false)
	private String authors;
	
	@Column(nullable = false)
	private LocalDate publicationYear;

	@Column(unique = true, nullable = false)
	private String ISBN;
	
	@Column(columnDefinition = "INT default 1")
	private int noOfCopies;
	
    @Column(columnDefinition = "boolean default true")
	private boolean isAvailable;
	
	
	@OneToMany(mappedBy="borrowedBook", fetch = FetchType.LAZY)
	private List<BorrowingRecord> listOfBorrowingRecords;


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}


	public String getAuthors() {
		return authors;
	}


	public void setAuthors(String authorName) {
		this.authors = authorName;
	}


	public LocalDate getPublicationYear() {
		return publicationYear;
	}


	public void setPublicationYear(LocalDate publicationYear) {
		this.publicationYear = publicationYear;
	}


	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public int getNoOfCopies() {
		return noOfCopies;
	}


	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public List<BorrowingRecord> getListOfBorrowingRecords() {
		return listOfBorrowingRecords;
	}


	public void setListOfBorrowingRecords(List<BorrowingRecord> listOfBorrowingRecords) {
		this.listOfBorrowingRecords = listOfBorrowingRecords;
	}


	
	
	


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", authorName=" + authors
				+ ", publicationYear=" + publicationYear + ", ISBN=" + ISBN + ", noOfCopies=" + noOfCopies
				+ ", isAvailable=" + isAvailable + "]";
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Book cloneCopy = new Book();
		cloneCopy.setBookId(this.bookId);
		cloneCopy.setBookTitle(this.bookTitle);
		cloneCopy.setAuthors(this.authors);
		cloneCopy.setISBN(this.ISBN);
		cloneCopy.setNoOfCopies(this.noOfCopies);
		cloneCopy.setAvailable(this.isAvailable);
		cloneCopy.setPublicationYear(this.publicationYear);
		
		List<BorrowingRecord> cloneList = new ArrayList<BorrowingRecord>();
		for(BorrowingRecord record : this.listOfBorrowingRecords) {
			cloneList.add(record);
		}
		
		cloneCopy.setListOfBorrowingRecords(cloneList);
		return cloneCopy;
	}
	
	
	
	
		
}
