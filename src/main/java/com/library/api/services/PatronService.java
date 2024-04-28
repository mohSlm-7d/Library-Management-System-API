package com.library.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.library.api.beans.Patron;
import com.library.api.daos.PatronRepository;

import jakarta.transaction.Transactional;

// Generic Component type
//@Component

// Singelton is the default scope
//@Scope(value = "singelton")


// Component type (bean definition alternative) specialized as a 
// Service (encapsulates business logic)
@Service
@Transactional
public class PatronService {
	@Autowired
	private PatronRepository patronRepository;
	
	
	public Optional<List<Patron>> findAllPatrons(){
		try{
			List<Patron> listOfPatrons = new ArrayList<Patron>();
		
			this.patronRepository.findAll().forEach(patron -> {
				listOfPatrons.add(patron);
			});;
			return Optional.ofNullable(listOfPatrons);
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
	
	

	public Optional<Patron> findPatronById(int patronId) {
		try{
			return this.patronRepository.findById(patronId);		
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
	
	
	
	

	public Optional<Patron> addNewPatron(Patron newPatron) {
		try{
			return Optional.of(this.patronRepository.save(newPatron));
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	

	public Optional<Patron> updatePatronById(int patronId, Patron updatedPatron) {
		try {
			Optional<Patron> patronToUpdate = this.patronRepository.findById(patronId);
			if(patronToUpdate.isPresent()) {
				this.patronRepository.deleteById(patronId);
//				updatedPatron.setpatronId(patronId);
				
				return Optional.of(this.patronRepository.save(updatedPatron));
			}
			
			return Optional.empty();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	
	
	

	public Optional<Patron> deletePatronById(int patronId) {
		try{
			Optional<Patron> patronToDelete = this.patronRepository.findById(patronId);
		
			if(patronToDelete.isPresent()) {
				this.patronRepository.deleteById(patronId);
				return Optional.of(patronToDelete.get());
			}
			
			return Optional.empty();
		}catch(Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}
}
