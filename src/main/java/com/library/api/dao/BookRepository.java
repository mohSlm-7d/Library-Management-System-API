package com.library.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.library.api.bean.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
