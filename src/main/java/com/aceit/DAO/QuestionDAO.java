package com.aceit.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.aceit.model.*;

@Component
public class QuestionDAO extends DAO {
	

    public QuestionDAO() {
    }

    public void saveQuestionToDb(Question question) {
        try {
            begin();
            getSession().save(question);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
    }
// Display all to tutor based on his subject

    public List<Question> displayAllUnAnsweredQuestionsByTutorSubject(String tutorSubject) {

        List<Question> listAllQuestions = null;
        try {
            begin();
            Query query = getSession().createQuery("from Question where subject= :tutorSubject and status= :status");
            query.setParameter("tutorSubject", tutorSubject);
            query.setParameter("status", false);
            listAllQuestions = query.list();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
        return listAllQuestions;
    }

    //Displaying  all unanswered questions posted by the student
    public List<Question> displayAllUnAnswredQuestionsByStudentId(int studentId) {

        System.out.println("loggedInStudentId in question DAO:" + studentId);
        List<Question> listAllQuestionsOfStudent = null;
        try {
            begin();
            //modify the query to display question based on student Id
            String hql = "From Question where StudentId =:stuId and status =:Status";
            Query query = getSession().createQuery(hql);
            query.setParameter("stuId", studentId);
            query.setParameter("Status", false);
            listAllQuestionsOfStudent = query.list();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
        return listAllQuestionsOfStudent;
    }

    public List<Question> displayAllAnswredQuestionsByStudentId(int studentId) {

        System.out.println("loggedInStudentId in question DAO:" + studentId);
        List<Question> listAllQuestionsOfStudent = null;
        try {
            begin();
            //modify the query to display question based on student Id
            String hql = "From Question where StudentId =:stuId and status =:Status";
            Query query = getSession().createQuery(hql);
            query.setParameter("stuId", studentId);
            query.setParameter("Status", true);
            listAllQuestionsOfStudent = query.list();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
        return listAllQuestionsOfStudent;
    }

//
    public void deleteUnansweredQuestion(int delQuesId) {
        try {
            //delete item object in the database
            begin();
            String hql = "delete from Question where questionId = :delId";
            Query query = getSession().createQuery(hql);
            query.setParameter("delId", delQuesId);
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            commit();

        } catch (Exception e) {
            rollback();

            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
    }


    public Question getQuesObj(int questionsId) {

        Question ques = getSession().get(Question.class, questionsId);
        return ques;
    }


}
