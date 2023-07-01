package com.aceit.DAO;

import java.util.List;

import org.hibernate.query.Query;

import com.aceit.model.*;

public class AnswerDAO extends DAO {
	

    public void saveAnswerToDb(Answer answer) {
        try {
            begin();
            getSession().save(answer);

            if (answer.getQuestionFK() != null) {

                System.out.println("into question status");

                String hql = "UPDATE Question set status = :status " + "WHERE id = :answerId";
                Query query = getSession().createQuery(hql);
                query.setParameter("status", true);
                query.setParameter("answerId", answer.getQuestionFK().getQuestionId());
                int result = query.executeUpdate();
                System.out.println("Rows affected: " + result);

            }

            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
    }

    public List<Answer> displayAllAnswersByTutorIdAndSubject(String subject, int tutorId) {
        List<Answer> listAllAnswers = null;
        try {
            begin();
            Query query = getSession().createQuery("from Answer where answeredSubject= :tutorSubject and TutorId= :tutId");
            query.setParameter("tutorSubject", subject);
            query.setParameter("tutId", tutorId);
            listAllAnswers = query.list();
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
        return listAllAnswers;

    }

    public Answer displayAnswersById(int answerId) {
        Answer answer = getSession().get(Answer.class, answerId);
        return answer;
    }

    public void update(Answer ans) {
        try {
            begin();
            getSession().update(ans);
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
    }

    public Student getStudentObjOfQuestion(int QId) {
        Student getStudentObjOfQuestion = null;

        try {
            begin();

//            String hql = "select Student.studentId from Student inner join Student.Question where questionId = :quesid”;
//            Query query = getSession().createQuery("from Student inner join Student.Question where questionId = :quesid");
            Query query = getSession().createQuery("select studentId from Question where questionId = :quesid");

            query.setParameter("quesid", QId);

            getStudentObjOfQuestion = (Student) query.uniqueResult();
            if (getStudentObjOfQuestion == null) {
                return null;
            }
            commit();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
        return getStudentObjOfQuestion;
    }

    public Question getQuestionObjFromAns(int AId) {
        Question getQuestionObjFromAns = null;

        try {
            begin();

//            String hql = "select Student.studentId from Student inner join Student.Question where questionId = :quesid”;
//            Query query = getSession().createQuery("from Student inner join Student.Question where questionId = :quesid");
            Query query = getSession().createQuery("select questionFK from Answer where answerId = :ansid");

            query.setParameter("ansid", AId);

            getQuestionObjFromAns = (Question) query.uniqueResult();
            if (getQuestionObjFromAns == null) {
                return null;
            }
            commit();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        } finally {
            close();
        }
        return getQuestionObjFromAns;
    }

    public Answer displayAnswersToStudent(int questionsId) {
       Answer displayAnswersToStudent = null;
        try {
            begin();
              System.out.println("XXXXXXQid:"+questionsId);

            Query query = getSession().createQuery("from Answer where QuestionId= :qId");
            query.setParameter("qId", questionsId);
            displayAnswersToStudent = (Answer)query.uniqueResult();
           
            commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            close();
        }
        return displayAnswersToStudent;

    }
    


}
