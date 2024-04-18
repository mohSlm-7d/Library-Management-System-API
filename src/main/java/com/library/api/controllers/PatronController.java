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

import com.library.api.beans.Patron;
import com.library.api.services.PatronService;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
	@Autowired
	private PatronService patronService;
	
	
//	● GET /api/patrons: Retrieve a list of all patrons.
	@GetMapping
	public ResponseEntity<Object> retrieveAllpatrons(){
		try{
			Optional<List<Patron>> listOfPatrons = this.patronService.findAllPatrons();
			if(listOfPatrons.isPresent()) {
				return ResponseEntity.ok(listOfPatrons.orElse(new ArrayList<Patron>()));				
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
	}
	
	
//	● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
	@GetMapping("{id}")
	public ResponseEntity<Object> retrievepatronById(@PathVariable("id") int patronId) {
		Optional<Patron> requestedPatron = this.patronService.findPatronById(patronId);		
		if(requestedPatron.isPresent()) {
			return ResponseEntity.ok(requestedPatron.get());
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	
	
//	● POST /api/patrons: Add a new patron to the system.
	@PostMapping
	public ResponseEntity<Object> addNewpatron(@RequestBody() Patron newpatron) {
		try{
			Optional<Patron> addedPatron = this.patronService.addNewPatron(newpatron);
			if(addedPatron.isPresent()) {
				return ResponseEntity.ok(addedPatron.get());
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}
	
	
//	● PUT /api/patrons/{id}: Update an existing patron's information.
	@PutMapping("{id}")
	public ResponseEntity<Object> updatepatronById(@PathVariable("id") int patronId, @RequestBody() Patron updatedpatron) {
		try {
			Optional<Patron> updatedPatronToReturn = this.patronService.updatePatronById(patronId, updatedpatron);
			if(updatedPatronToReturn.isPresent()) {
				return ResponseEntity.ok(updatedPatronToReturn.get());
			}
			
			return ResponseEntity.badRequest().build();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
	}

	
	
	
//	● DELETE /api/patrons/{id}: Remove a patron from the system.
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletepatronById(@PathVariable("id") int patronId) {
		try{
			Optional<Patron> patronToDelete = this.patronService.deletePatronById(patronId);
		
			if(patronToDelete.isPresent()) {
				return ResponseEntity.ok(patronToDelete.get());
			}
			
			return ResponseEntity.badRequest().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
	}
}
