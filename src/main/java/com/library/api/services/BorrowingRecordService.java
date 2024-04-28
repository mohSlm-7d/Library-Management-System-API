package com.library.api.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.internal.hbm.AbstractEntitySourceImpl;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.library.api.beans.Book;
import com.library.api.beans.BorrowingRecord;
import com.library.api.beans.BorrowingRecordId;
import com.library.api.beans.Patron;
import com.library.api.daos.BorrowingRecordRepository;

import ch.qos.logback.core.Context;
import jakarta.transaction.Transactional;

// Generic Component type
//@Component

// Singelton is the default scope
//@Scope(value = "singleton")


// Component type (bean definition alternative) specialized as a 
// Service (encapsulates business logic)
@Service
@Transactional
public class BorrowingRecordService {
	@Autowired
	private BorrowingRecordRepository borrowingRecordRepository;
	
	@Autowired
	private ApplicationContext context;
	
	
	public Optional<BorrowingRecord> borrowBook(BorrowingRecord newBorrowingRecord){
		try{
			BookService bookService = (BookService) context.getBean("bookService");
			PatronService patronService = (PatronService) context.getBean("patronService");
			
			
			Optional<Book> borrowedBook = bookService.findBookById(newBorrowingRecord.getBorrowedBook().getBookId());
			Optional<Patron> borrowingPatron = patronService.findPatronById(newBorrowingRecord.getBorrowingPatron().getPatronId());
			if(borrowedBook.isPresent() && borrowingPatron.isPresent()) {
				newBorrowingRecord.setBorrowedBook(borrowedBook.get());
				newBorrowingRecord.setBorrowingPatron(borrowingPatron.get());
				
				BorrowingRecordId borrowingRecordId = (BorrowingRecordId) context.getBean("borrowingRecordId");
				borrowingRecordId.setBorrowingPatron(borrowingPatron.get());
				borrowingRecordId.setBorrowedBook(borrowedBook.get());
				
				if(this.borrowingRecordRepository.findById(borrowingRecordId).isPresent()) {
					return Optional.empty();
				}
				
				return Optional.ofNullable(this.borrowingRecordRepository.save(newBorrowingRecord));
			}
			
			
			return Optional.empty();
			
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.of(null);
		}
	}
	
	public Optional<BorrowingRecord> returnBook(BorrowingRecord updatedBorrowingRecord){
		try{
			BookService bookService = (BookService) context.getBean("bookService");
			PatronService patronService = (PatronService) context.getBean("patronService");
			
			
			Optional<Book> borrowedBook = bookService.findBookById(updatedBorrowingRecord.getBorrowedBook().getBookId());
			Optional<Patron> borrowingPatron = patronService.findPatronById(updatedBorrowingRecord.getBorrowingPatron().getPatronId());
			if(borrowedBook.isPresent() && borrowingPatron.isPresent()) {
				updatedBorrowingRecord.setBorrowedBook(borrowedBook.get());
				updatedBorrowingRecord.setBorrowingPatron(borrowingPatron.get());
				
				BorrowingRecordId borrowingRecordId = (BorrowingRecordId) context.getBean("borrowingRecordId");
				borrowingRecordId.setBorrowingPatron(borrowingPatron.get());
				borrowingRecordId.setBorrowedBook(borrowedBook.get());
				
				Optional<BorrowingRecord> storedBorrowingRecord = this.borrowingRecordRepository.findById(borrowingRecordId);
				if(!storedBorrowingRecord.isPresent()) {
					return Optional.empty();
				}
				
				if(updatedBorrowingRecord.getBorrowingDate() == null || updatedBorrowingRecord.getBorrowingDate().compareTo(storedBorrowingRecord.get().getBorrowingDate()) != 0) {
					updatedBorrowingRecord.setBorrowingDate(storedBorrowingRecord.get().getBorrowingDate());
				}
				
				if(updatedBorrowingRecord.getReturnDate() == null || updatedBorrowingRecord.getReturnDate().compareTo(LocalDate.now()) < 0) {
					return Optional.empty();
				}
				
				
				this.borrowingRecordRepository.deleteById(borrowingRecordId);
				return Optional.ofNullable(this.borrowingRecordRepository.save(updatedBorrowingRecord));
			}
			
			return Optional.empty();
			
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.of(null);
		}
		
	}
	
	
	
	
	
//	public List<BorrowingRecord> getAllRecords(){
//		List<BorrowingRecord> listOfRecords = new ArrayList<BorrowingRecord>();
//		System.out.println("---------------------- listOfRecords ---------------------------");
////		this.borrowingRecordRepository.findAll().forEach(record -> {
////			listOfRecords.add(record);
////		});
//		Iterator<BorrowingRecord> iterator = this.borrowingRecordRepository.findAll().iterator();
//		System.out.println("iterator.next(): " + iterator.next());
////		while(iterator.hasNext()) {
////			System.out.println("In Iterator Loop>>>>");
////			System.out.println(iterator.next());
////		}
//		System.out.println("------------------ AFTERRRRRRRR listOfRecords--------------------");
//		return new ArrayList<BorrowingRecord>();
//	}
	
	
}
