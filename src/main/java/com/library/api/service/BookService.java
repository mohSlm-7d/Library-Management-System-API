package com.library.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.library.api.bean.Book;
import com.library.api.dao.BookRepository;

import jakarta.transaction.Transactional;

@Component
//@Scope(value = "singleton")
@Transactional
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	
	public Optional<List<Book>> findAllBooks(){
		try{
			List<Book> listOfBooks = new ArrayList<Book>();
		
			this.bookRepository.findAll().forEach(book -> {
				listOfBooks.add(book);
			});;
			return Optional.ofNullable(listOfBooks);
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
	
	

	public Optional<Book> findBookById(int bookId) {
		try{
			return this.bookRepository.findById(bookId);		
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	
	
	

	public Optional<Book> addNewBook(Book newBook) {
		try{
			return Optional.of(this.bookRepository.save(newBook));
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	

	public Optional<Book> updateBookById(int bookId, Book updatedBook) {
		try {
			Optional<Book> bookToUpdate = this.bookRepository.findById(bookId);
			if(bookToUpdate.isPresent()) {
				this.bookRepository.deleteById(bookId);
//				updatedBook.setBookId(bookId);
				
				return Optional.of(this.bookRepository.save(updatedBook));
			}
			
			return Optional.empty();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	
	
	

	public Optional<Book> deleteBookById(int bookId) {
		try{
			Optional<Book> bookToDelete = this.bookRepository.findById(bookId);
		
			if(bookToDelete.isPresent()) {
				this.bookRepository.deleteById(bookId);
				return Optional.of(bookToDelete.get());
			}
			
			return Optional.empty();
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
}
