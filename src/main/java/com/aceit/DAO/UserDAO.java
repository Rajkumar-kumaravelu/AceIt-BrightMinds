package com.aceit.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.aceit.model.Login;
import com.aceit.model.User;

@Component
public class UserDAO extends DAO {
	
	 public boolean validateLoggedInUserName(String userName, String pass) {
	        try {

	            Query query = getSession().createQuery(
	                    "From User where userName=:username and password=:password");
	            query.setParameter("username", userName);
	            query.setParameter("password", pass);
	            User result = (User) query.uniqueResult();

	            String role = result.getRole().toString();

	            System.out.println("The Valid User loggedin Role is: " + role);

	            if (result == null) {
	                return false;  //the userName and password is not present in the DB and not valid
	            }

	        } catch (Exception e) {

	            System.out.println(e.getMessage());
	            return false;
	        } finally {
	            close();
	        }

	        return true; // if the userName and password is valid

	    }
	 
	 public void registerUserDetails(User user) {
	        try {
	            begin();
	            getSession().save(user);
	            commit();
	        } catch (Exception e) {
	            System.out.println("Exception: " + e.getMessage());
	        } finally {
	            close();
	        }
	    }

	    public boolean checkUserNameExists(String userName) {
	        try {
	            begin();
	            Query query = getSession().createQuery("From User where userName=:username");
	            query.setParameter("username", userName);
	            List list = query.list();
	            commit();
	            if (list.size() == 0) {
	                return false; // no 
	            }
	        } catch (Exception e) {

	            System.out.println(e.getMessage());
	            return false;
	        } finally {
	            close();
	        }
	        return true;
	    }

	    public String getValidUserRole(Login login) {
	        String role = null;

	        String userName = login.getUserName();
	        String pass = login.getPassword();
	        try {

	            Query query = getSession().createQuery(
	                    "From User where userName=:username and password=:password");
	            query.setParameter("username", userName);
	            query.setParameter("password", pass);
	            User result = (User) query.uniqueResult();

	            role = result.getRole().toString();

	            System.out.println("The Valid User loggedin Role in userdao: " + role);

	        } catch (Exception e) {

	            System.out.println(e.getMessage());
	        } finally {
	            close();
	        }

	        return role; // if the userName and password is valid

	    }

}
