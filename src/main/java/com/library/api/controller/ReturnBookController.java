package com.library.api.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.bean.Book;
import com.library.api.bean.BorrowingRecord;
import com.library.api.bean.Patron;
import com.library.api.service.BorrowingRecordService;

@RestController
@RequestMapping("/api/return/")
public class ReturnBookController {
	@Autowired
	private BorrowingRecordService borrowingRecordService;
	
	@Autowired
	private ApplicationContext context;
	
//	● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
	@PutMapping("/{bookId}/patron/{patronId}")
	public ResponseEntity<Object> returnBorrowedBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId, @RequestBody() String jsonBody) {
		try {
			JSONObject recordObj = new JSONObject(jsonBody);
			
			
			BorrowingRecord newBorrowingRecord = (BorrowingRecord) context.getBean("borrowingRecord");
			
			
			Patron borrowingPatron = (Patron) context.getBean("patron");
			borrowingPatron.setPatronId(patronId);
			newBorrowingRecord.setBorrowingPatron(borrowingPatron);
			
			Book borrowedBook = (Book) context.getBean("book");
			borrowedBook.setBookId(bookId);
			newBorrowingRecord.setBorrowedBook(borrowedBook);
			
			
			if(recordObj.get("borrowingDate").equals(null)) {
				newBorrowingRecord.setBorrowingDate(null);
			}
			else{
				newBorrowingRecord.setBorrowingDate(LocalDate.parse(recordObj.get("borrowingDate").toString()));
			}
			
			
			
			if(recordObj.get("returnDate").equals(null)) {
				newBorrowingRecord.setReturnDate(null);
			}
			else {
				newBorrowingRecord.setReturnDate(LocalDate.parse(recordObj.get("returnDate").toString()));
			}
			
			Optional<BorrowingRecord> addedRecordToReturn = this.borrowingRecordService.returnBook(newBorrowingRecord);
			if(addedRecordToReturn.equals(null)) {
				throw new Exception("internal server error");
			}
			if(addedRecordToReturn.isEmpty()) {
				return ResponseEntity.badRequest().body("The borrowing record details are incorrect or the book return date is invalid (book return date cannot be in the past)!");
			}
			
			return ResponseEntity.ok(addedRecordToReturn);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}
}
