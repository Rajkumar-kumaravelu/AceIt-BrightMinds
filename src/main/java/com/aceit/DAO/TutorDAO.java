package com.aceit.DAO;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.aceit.model.Login;
import com.aceit.model.Tutor;

@Component
public class TutorDAO extends DAO  {

    public TutorDAO() {
    }
    
    public void registerTutorDetails(Tutor tutor) {
        try {
        begin();
        getSession().save(tutor);
        commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    
    
    public Tutor getLoggedInTutorObj(Login login) {
        Tutor loggedInTutorObj = null;
        
        try {
            begin();
            
            Query query = getSession().createQuery(
                    "From Tutor where userName=:username and password=:password");
            query.setParameter("username", login.getUserName());
            query.setParameter("password", login.getPassword());
            loggedInTutorObj = (Tutor) query.uniqueResult();
            if (loggedInTutorObj == null) {
                return null;
            }
            commit();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
        return loggedInTutorObj;
    }
 

}
