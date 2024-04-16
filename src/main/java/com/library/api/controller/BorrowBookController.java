package com.library.api.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.library.api.bean.Book;
import com.library.api.bean.BorrowingRecord;
import com.library.api.bean.Patron;
import com.library.api.service.BookService;
import com.library.api.service.BorrowingRecordService;
import com.library.api.service.PatronService;

@RestController
@RequestMapping("/api/borrow")
public class BorrowBookController {
	@Autowired
	private BorrowingRecordService borrowingRecordService;
	@Autowired
	private ApplicationContext context;
	
//	● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to
//	borrow a book.
	@PostMapping("/{bookId}/patron/{patronId}")
	public ResponseEntity<Object> borrowBook(@PathVariable("bookId") int bookId, @PathVariable("patronId") int patronId, @RequestBody() String jsonBody) {
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
				newBorrowingRecord.setBorrowingDate(LocalDate.now());
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
			
			
			Optional<BorrowingRecord> addedRecordToReturn = this.borrowingRecordService.borrowBook(newBorrowingRecord);
			if(addedRecordToReturn.equals(null)) {
				throw new Exception("internal server error");
			}
			if(addedRecordToReturn.isEmpty()) {
				return ResponseEntity.badRequest().body("The borrowing record already exists (This patron already borrowed this book)!");
			}
			
			return ResponseEntity.ok(addedRecordToReturn);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}
	
	
//	@GetMapping
//	public ResponseEntity<Object> getAllRecords(){
//		try{
//			return ResponseEntity.ok(this.borrowingRecordService.getAllRecords());
////			return ResponseEntity.ok(new BorrowingRecord());
//		}catch(Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.internalServerError().body(e);
//		}
//	}
}
