package com.library.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.beans.Book;
import com.library.api.dao.BookRepository;
import com.library.api.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	BookService bookService;
	
	
//	● GET /api/books: Retrieve a list of all books.
	@GetMapping
	public ResponseEntity<Object> retrieveAllBooks(){
		try{
			Optional<List<Book>> listOfBooks = this.bookService.findAllBooks();
			if(listOfBooks.isPresent()) {
				return ResponseEntity.ok(listOfBooks.orElse(new ArrayList<Book>()));				
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
	}
	
	
//	● GET /api/books/{id}: Retrieve details of a specific book by ID.
	@GetMapping("{id}")
	public ResponseEntity<Object> retrieveBookById(@PathVariable("id") int bookId) {
		Optional<Book> requestedBook = this.bookService.findBookById(bookId);		
		if(requestedBook.isPresent()) {
			return ResponseEntity.ok(requestedBook.get());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	
	
//	● POST /api/books: Add a new book to the library.
	@PostMapping
	public ResponseEntity<Object> addNewBook(@RequestBody() Book newBook) {
		try{
			Optional<Book> addedBook = this.bookService.addNewBook(newBook);
			if(addedBook.isPresent()) {
				return ResponseEntity.ok(addedBook.get());
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}
	
	
//	● PUT /api/books/{id}: Update an existing book's information.
	@PutMapping("{id}")
	public ResponseEntity<Object> updateBookById(@PathVariable("id") int bookId, @RequestBody() Book updatedBook) {
		try {
			Optional<Book> updatedBookToReturn = this.bookService.updateBookById(bookId, updatedBook);
			if(updatedBookToReturn.isPresent()) {
				return ResponseEntity.ok(updatedBookToReturn.get());
			}
			
			return ResponseEntity.badRequest().build();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}

	
	
	
//	● DELETE /api/books/{id}: Remove a book from the library.
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteBookById(@PathVariable("id") int bookId) {
		try{
			Optional<Book> bookToDelete = this.bookService.deleteBookById(bookId);
		
			if(bookToDelete.isPresent()) {
				return ResponseEntity.ok(bookToDelete.get());
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
	}
}
