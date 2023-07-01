package com.aceit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="tutorTable")
public class Tutor {
	
	 	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int tutorId;
	    
	    private String firstName;
	    private String lastName;
	    private String userName;
	    private String email;
	    private String password;
	    private String subject;
	    private Roles role;
		public int getTutorId() {
			return tutorId;
		}
		public void setTutorId(int tutorId) {
			this.tutorId = tutorId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public Roles getRole() {
			return role;
		}
		public void setRole(Roles role) {
			this.role = role;
		}
	    
}
