package com.library.api.beans;

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
//@Embeddable
@Entity
@Component()
@Scope(value="prototype")
public class Patron {
	@Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	private int patronId;
	
	@Column(nullable = false)
	private String patronName;
	
	@Column(nullable = false)
	private String patronGender;
	
	@Column(unique = true, nullable = false)
	private String patronPhoneNo;
	
	@Column(unique = true, nullable = false)
	private String patronEmail;
	
	
	
	@OneToMany(mappedBy="borrowingPatron", fetch = FetchType.LAZY)
	private List<BorrowingRecord> listOfBorrowingRecords;

	public int getPatronId() {
		return patronId;
	}

	public void setPatronId(int patronId) {
		this.patronId = patronId;
	}

	public String getPatronName() {
		return patronName;
	}

	public void setPatronName(String partonName) {
		this.patronName = partonName;
	}

	public String getPatronGender() {
		return patronGender;
	}

	public void setPatronGender(String patronGender) {
		this.patronGender = patronGender;
	}

	public String getPatronPhoneNo() {
		return patronPhoneNo;
	}

	public void setPatronPhoneNo(String patronPhoneNo) {
		this.patronPhoneNo = patronPhoneNo;
	}

	public String getPatronEmail() {
		return patronEmail;
	}

	public void setPatronEmail(String patronEmail) {
		this.patronEmail = patronEmail;
	}

	public List<BorrowingRecord> getListOfBorrowingRecords() {
		return listOfBorrowingRecords;
	}

	public void setListOfBorrowingRecords(List<BorrowingRecord> listOfBorrowingRecords) {
		this.listOfBorrowingRecords = listOfBorrowingRecords;
	}

	

	@Override
	public String toString() {
		return "Patron [patronId=" + patronId + ", patronName=" + patronName + ", patronGender=" + patronGender
				+ ", patronPhoneNo=" + patronPhoneNo + ", patronEmail=" + patronEmail + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Patron cloneCopy = new Patron();
		cloneCopy.setPatronId(this.patronId);
		cloneCopy.setPatronName(this.patronName);
		cloneCopy.setPatronEmail(this.patronEmail);
		cloneCopy.setPatronGender(this.patronGender);
		cloneCopy.setPatronPhoneNo(this.patronPhoneNo);
		
		List<BorrowingRecord> cloneList = new ArrayList<BorrowingRecord>();
		for(BorrowingRecord record : this.listOfBorrowingRecords) {
			cloneList.add(record);
		}
		
		cloneCopy.setListOfBorrowingRecords(cloneList);
		return cloneCopy;
	}
	
	
	
	
	
	
}
