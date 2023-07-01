package com.aceit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aceit.DAO.*;
import com.aceit.model.*;
import com.aceit.validator.AnswerPostValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AnswerPostController {

	@Autowired
	AnswerPostValidator answerPostValidator;

	@GetMapping(value = "/tutorQuestionsView.htm")
	public ModelAndView tutorQuestionsViewGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Listing all question posted by student -- From Tutor Dashboard Page to
	// Opening Tutor Question View
	@RequestMapping(value = "/tutorQuestionsView.htm", method = RequestMethod.POST)
	public ModelAndView tutorQuestionsView(HttpServletRequest request, QuestionDAO questionDAO) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {

				Tutor tutor = (Tutor) session.getAttribute("loggedInTutorObj");

				String tutorSubject = tutor.getSubject();
				System.out.println("tutorSubject in answerPostController:-" + tutorSubject);

				request.setAttribute("question", new Question());
				request.setAttribute("answer", new Answer());

				List<Question> listallQuestion = questionDAO.displayAllUnAnsweredQuestionsByTutorSubject(tutorSubject);
				session.setAttribute("listallQuestion", listallQuestion);
				return new ModelAndView("tutorQuestionsView");

			}
		}
		return null;
	}

	@GetMapping(value = "/tutorAnswerPostingPage.htm")
	public ModelAndView tutorAnswerPostingPageGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// From tutor question view and selecting answer, open answer posting page
	@RequestMapping(value = "/tutorAnswerPostingPage.htm", method = RequestMethod.POST)
	public ModelAndView tutorAnswerPostingPage(@ModelAttribute("question") Question question,
			HttpServletRequest request, Answer answer, Model model, AnswerDAO answerDAO) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {

				System.out.println("questiontoAnswerobj:-" + question.getQuestion());

				System.out.println("questionidtoAnswerobj:-" + question.getQuestionId());
//        int studentidtobesetinanswertable = question.getStudentId().getStudentId();

				Student StudentObjFromQuestion = answerDAO.getStudentObjOfQuestion(question.getQuestionId());

				session.setAttribute("StudentObjFromQuestion", StudentObjFromQuestion);

				System.out.println(
						"======studentidtobesetinanswertable=======:-" + StudentObjFromQuestion.getStudentId());
				System.out.println(
						"======studentnametobesetinanswertable=======:-" + StudentObjFromQuestion.getUserName());

				// get student object using questionID
				request.getSession().setAttribute("ques", question);
				return new ModelAndView("tutorAnswerPostingPage");
			}
		}
		return null;
	}

	@GetMapping(value = "/answerSubmit.htm")
	public ModelAndView answerSubmitGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// From tutor answer posting page to Submitting answer
	@RequestMapping(value = "/answerSubmit.htm", method = RequestMethod.POST)
	public ModelAndView answerSubmit(HttpServletRequest request, @ModelAttribute("answer") Answer answer,
			BindingResult result, SessionStatus status, AnswerDAO answerDAO, Question question) throws Exception {

		answerPostValidator.validate(answer, result);

//        System.out.println("answer:" + answer.getAnswer());
		if (result.hasErrors()) {
			System.out.println("into error");
			return new ModelAndView("tutorAnswerPostingPage");
		}

		status.setComplete();

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {

				Question ques = (Question) session.getAttribute("ques");

				Student student = (Student) session.getAttribute("StudentObjFromQuestion");
				System.out.println("======studentidinanswersubmission=======:-" + student.getStudentId());

				ques.setStudentId(student);
				answer.setQuestionFK(ques);
				answer.setAnsweredQuestion(ques.getQuestion());
				answer.setAnsweredSubject(ques.getSubject());

				Tutor tutorId = (Tutor) session.getAttribute("loggedInTutorObj");
				System.out.println("tutorId in answer:" + tutorId.getTutorId());
				answer.setTutor(tutorId);

				answerDAO.saveAnswerToDb(answer);
				System.out.println("answered saved:");
				System.out.println("QuesId in answer:" + answer.getQuestionFK().getQuestionId());

				return new ModelAndView("answerPostingSuccess");
			}
		}
		return null;
	}

	@GetMapping(value = "/returnTutorDashboardPage.htm")
	public ModelAndView returnTutorDashboardPageGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Redirection to dashboard page
	@RequestMapping(value = "/returnTutorDashboardPage.htm", method = RequestMethod.POST)
	public ModelAndView returnTutorDashboardPage() throws Exception {
		return new ModelAndView("tutorDashboardPage");
	}

	@GetMapping(value = "/tutorAnswersView.htm")
	public ModelAndView tutorAnswersViewGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Listing all answers of the tutor -- Tutor answer view page
	@RequestMapping(value = "/tutorAnswersView.htm", method = RequestMethod.POST)
	public ModelAndView tutorAnswersView(HttpServletRequest request, AnswerDAO answerDAO, Answer ans) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {
				Tutor tutor = (Tutor) session.getAttribute("loggedInTutorObj");

				int tutorId = tutor.getTutorId();
				ans.setTutor(tutor);
				String tutorSubjectFromAnsObj = ans.getTutor().getSubject();
				System.out.println("tutorSubjectFromAnsObj" + tutorSubjectFromAnsObj);

				List<Answer> listallAnswers = answerDAO.displayAllAnswersByTutorIdAndSubject(tutorSubjectFromAnsObj,
						tutorId);
				request.setAttribute("listallAnswers", listallAnswers);

				return new ModelAndView("tutorAnswersView");
			}
		}
		return null;
	}

	@GetMapping(value = "/tutorAnswerUpdate.htm")
	public ModelAndView tutorAnswerUpdateGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// From tutor answer view page to opening answer update page
	@RequestMapping(value = "/tutorAnswerUpdate.htm", method = RequestMethod.POST)
	public ModelAndView tutorAnswerUpdate(HttpServletRequest request, Answer answer, AnswerDAO answerDAO, Model model)
			throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {

				Integer answerId = Integer.valueOf(request.getParameter("id"));
				System.out.println("answerId from tutor answerview for updating is" + answerId);

				Answer getAnswerObjectById = answerDAO.displayAnswersById(answerId);
				model.addAttribute("displayAnswerById", getAnswerObjectById);

				Question questionObjFromAnswer = answerDAO.getQuestionObjFromAns(answerId);

				System.out.println("questionObjFromAnswer" + questionObjFromAnswer.getQuestionId());

				Student StudentObjFromQuestion = answerDAO
						.getStudentObjOfQuestion(questionObjFromAnswer.getQuestionId());

				System.out.println(
						"*****StudentobjFromquestionObjFromAnswer*****" + StudentObjFromQuestion.getStudentId());

				session.setAttribute("getAnswerObjectById", getAnswerObjectById);

				return new ModelAndView("tutorAnswerUpdate");
			}
		}
		return null;
	}

	@GetMapping(value = "/answerUpdateSubmit.htm")
	public ModelAndView answerUpdateSubmitGet(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Submitting updated answer
	@RequestMapping(value = "/answerUpdateSubmit.htm", method = RequestMethod.POST)
	public ModelAndView answerUpdate(HttpServletRequest request, @ModelAttribute("answer") Answer answer,
			BindingResult result, SessionStatus status, AnswerDAO answerDAO) throws Exception {

		answerPostValidator.validate(answer, result);

		System.out.println("answerId after update:" + answer.getAnswerId());
		System.out.println("answer after update:" + answer.getAnswer());

		if (result.hasErrors()) {
			return new ModelAndView("tutorAnswerUpdate");
		}

		status.setComplete();

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("tutor") && session.getAttribute("loggedInUser") == loggedInUser) {
				Answer getAnswerObjectById = (Answer) session.getAttribute("getAnswerObjectById");

				answer.setQuestionFK(getAnswerObjectById.getQuestionFK());
				answer.setAnsweredQuestion(getAnswerObjectById.getAnsweredQuestion());
				answer.setAnsweredSubject(getAnswerObjectById.getAnsweredSubject());

				Tutor tutorId = (Tutor) session.getAttribute("loggedInTutorObj");
				System.out.println("tutorId in answer:" + tutorId.getTutorId());
				answer.setTutor(tutorId);

				answerDAO.update(answer);
				return new ModelAndView("answerPostingSuccess");
			}
		}
		return null;
	}

}
