package com.niit.collabBackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	int Id;
	String message;

	public Message() {}
	
	public Message(int id,String message) {
		this.Id=id;
		this.message=message;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
