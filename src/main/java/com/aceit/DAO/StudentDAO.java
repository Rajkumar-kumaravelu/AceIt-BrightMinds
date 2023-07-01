package com.aceit.DAO;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.aceit.model.Login;
import com.aceit.model.Student;

@Component
public class StudentDAO extends DAO {
	


    public StudentDAO() {
    }

    public void registerStudentDetails(Student student) {
        try {
            begin();
            getSession().save(student);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
    }

    public Student getLoggedInStudentObj(Login login) {
        Student loggedInStudentObj = null;
        
        try {
            begin();
            
        
            
            Query query = getSession().createQuery(
                    "From Student where userName=:username and password=:password");
            query.setParameter("username", login.getUserName());
            query.setParameter("password", login.getPassword());
            loggedInStudentObj = (Student) query.uniqueResult();
            
      
            if (loggedInStudentObj == null) {
                return null;
            }
            commit();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
        return loggedInStudentObj;
    }


}
