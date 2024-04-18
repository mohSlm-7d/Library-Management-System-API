package com.library.api.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.library.api.beans.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
