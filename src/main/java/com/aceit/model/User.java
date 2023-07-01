package com.aceit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "userTable")
public class User {
	
	 	@Id
	    private String userName;
	    private String password;
	    private Roles role;
	    
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Roles getRole() {
			return role;
		}
		public void setRole(Roles role) {
			this.role = role;
		}
	    
	    

}
