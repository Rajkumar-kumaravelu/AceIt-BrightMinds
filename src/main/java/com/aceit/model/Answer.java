package com.aceit.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "answerTable")
public class Answer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;
    private String answer;
    private String ansComments;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QuestionId", referencedColumnName = "questionId")
    private Question questionFK;
    
    private String answeredQuestion;
    private String answeredSubject;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TutorId", referencedColumnName = "tutorId")
    private Tutor tutor;

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnsComments() {
		return ansComments;
	}

	public void setAnsComments(String ansComments) {
		this.ansComments = ansComments;
	}

	public Question getQuestionFK() {
		return questionFK;
	}

	public void setQuestionFK(Question questionFK) {
		this.questionFK = questionFK;
	}

	public String getAnsweredQuestion() {
		return answeredQuestion;
	}

	public void setAnsweredQuestion(String answeredQuestion) {
		this.answeredQuestion = answeredQuestion;
	}

	public String getAnsweredSubject() {
		return answeredSubject;
	}

	public void setAnsweredSubject(String answeredSubject) {
		this.answeredSubject = answeredSubject;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
    
    
    

}
