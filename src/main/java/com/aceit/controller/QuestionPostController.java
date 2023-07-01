package com.aceit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.aceit.DAO.*;
import com.aceit.model.*;
import com.aceit.validator.QuestionPostValidator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class QuestionPostController {

	@Autowired
	QuestionPostValidator questionPostValidator;

	// Display loginpage if the user is not present in session and the role is not
	// student

	@GetMapping(value = "/studentQuestionPostingPage.htm")
	public ModelAndView studentQuestionPostingPageGET(@ModelAttribute("question") Question question,
			HttpServletRequest request) throws Exception {

		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Displaying page for student to post question, when user is logged in session
	// and the role is student

	@PostMapping(value = "/studentQuestionPostingPage.htm")
	public ModelAndView studentQuestionPostingPage(@ModelAttribute("question") Question question,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {
				return new ModelAndView("studentQuestionPostingPage");

			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {
				return new ModelAndView("loginPage");
			}

		}
		return null;
	}

	// Display loginpage if the user is not present in session and the role is not
	// student

	@GetMapping(value = "/questionSubmit.htm")
	public ModelAndView questionSubmitGET(HttpServletRequest request) throws Exception {

		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	// Question submit by student

	@PostMapping(value = "/questionSubmit.htm")
	public ModelAndView questionSubmit(@ModelAttribute("question") Question question, BindingResult result,
			SessionStatus status, QuestionDAO questionDAO, HttpServletRequest request) throws Exception {

		questionPostValidator.validate(question, result);

		if (result.hasErrors()) {
			return new ModelAndView("studentQuestionPostingPage");
		}

		status.setComplete();
		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");

		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {

				Student studentId = (Student) session.getAttribute("loggedInStudentObj");
				question.setStudentId(studentId);

				questionDAO.saveQuestionToDb(question);
				return new ModelAndView("questionPostingSuccess");
			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {
				return new ModelAndView("loginPage");
			}

		}
		return null;
	}

	// Return to Login page is the user is not logged in
	@RequestMapping(value = "/returnStudentDashboardPage.htm", method = RequestMethod.GET)
	public ModelAndView returnStudentDashboardPageGET(HttpServletRequest request) throws Exception {

		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");

	}

	// Return to Student Dashboard Page
	@RequestMapping(value = "/returnStudentDashboardPage.htm", method = RequestMethod.POST)
	public ModelAndView returnStudentDashboardPage(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");


		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {
				return new ModelAndView("studentDashboardPage");
			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {
				return new ModelAndView("loginPage");
			}

		}
		return null;
	}

	// Return user to login page if the user is not logged into session
	@RequestMapping(value = "/studentQuestionView.htm", method = RequestMethod.GET)
	public ModelAndView studentQuestionViewGET(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");

	}

	// Listing all UnAnswered questions posted by the student
	@RequestMapping(value = "/studentQuestionView.htm", method = RequestMethod.POST)
	public ModelAndView studentQuestionView(HttpServletRequest request, QuestionDAO questionDAO) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");


		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {

				request.setAttribute("question", new Question());

				Student student = (Student) session.getAttribute("loggedInStudentObj");

				int studentId = student.getStudentId();
				System.out.println("stuId:" + studentId);

				List<Question> listallQuestionOfStudent = questionDAO
						.displayAllUnAnswredQuestionsByStudentId(studentId);
				request.setAttribute("listallQuestion", listallQuestionOfStudent);

				return new ModelAndView("studentQuestionView");
			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {
				return new ModelAndView("loginPage");
			}

		}
		return null;
	}

	// Delete Unanswered question
	@RequestMapping(value = "/studentQuestionDelete.htm", method = RequestMethod.GET)
	public ModelAndView studentQuestionDelete(HttpServletRequest request, QuestionDAO questionDAO) throws Exception {
		HttpSession session = request.getSession();

		request.setAttribute("question", new Question());
		request.setAttribute("answer", new Answer());

		int delId = Integer.parseInt(request.getParameter("delId"));
		System.out.println("delId:" + delId);
		questionDAO.deleteUnansweredQuestion(delId);

		Student student = (Student) session.getAttribute("loggedInStudentObj");
		int studentId = student.getStudentId();
		System.out.println("stuId:" + studentId);

		List<Question> listallQuestionOfStudent = questionDAO.displayAllUnAnswredQuestionsByStudentId(studentId);
		request.setAttribute("listallQuestion", listallQuestionOfStudent);
		return new ModelAndView("studentQuestionView");
	}

	// Return user to login page if the user is not logged into session
	@RequestMapping(value = "/studentAnswersQuestionView.htm", method = RequestMethod.GET)
	public ModelAndView studentAnswersQuestionViewGET(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");

	}

	// Listing all questions which are answered
	@RequestMapping(value = "/studentAnswersQuestionView.htm", method = RequestMethod.POST)
	public ModelAndView studentAnswersQuestionView(HttpServletRequest request, QuestionDAO questionDAO, Answer answer)
			throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");


		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {
				request.setAttribute("question", new Question());
				request.setAttribute("answer", new Answer());

				Student student = (Student) session.getAttribute("loggedInStudentObj");

				int studentId = student.getStudentId();
				System.out.println("stuId:" + studentId);

				List<Question> listallAnsweredQuestion = questionDAO.displayAllAnswredQuestionsByStudentId(studentId);
				request.setAttribute("listallAnsweredQuestion", listallAnsweredQuestion);

				return new ModelAndView("studentAnswersQuestionView");

			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {
				return new ModelAndView("loginPage");
			}

		}
		return null;
	}

	// Return user to login page if the user is not logged into session
	@RequestMapping(value = "/studentAnswerView.htm", method = RequestMethod.GET)
	public ModelAndView studentAnswerViewGET(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");

	}

	// Listing all answers
	@RequestMapping(value = "/studentAnswerView.htm", method = RequestMethod.POST)
	public ModelAndView studentAnswerView(HttpServletRequest request, AnswerDAO answerDAO, Answer answer,
			QuestionDAO questionDAO, Question question, Model model) throws Exception {

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");


		if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null) {

			if (loggedInUserRole.equalsIgnoreCase("student")) {

				request.setAttribute("question", new Question());
				request.setAttribute("answer", new Answer());

				int quesId = Integer.parseInt(request.getParameter("quesId"));

				System.out.println("XXXXXXQid:" + quesId);

				Question quesObj = questionDAO.getQuesObj(quesId);

				// quesObj.getQuestion();
				System.out.println("XXXXXXQuesfromqobj:" + quesObj.getQuestion());

//        Answer displayAnsweredToStudent = answerDAO.displayAnswersById2(quesObj);

				Answer displayAnsweredToStudent = answerDAO.displayAnswersToStudent(quesId);

				// request.setAttribute("displayAnsweredToStudent", displayAnsweredToStudent);
				model.addAttribute("displayAnsweredToStudent", displayAnsweredToStudent);

				return new ModelAndView("studentAnswerView");

			}
			if (session.getAttribute("loggedInUser") == null || session.getAttribute("loggedInUserRole") == null) {

				return new ModelAndView("loginPage");

			}

		}
		return null;
	}

	@RequestMapping(value = "/report.htm", method = RequestMethod.GET)
	public View report(HttpServletRequest request, AnswerDAO answerDAO) throws Exception {

		Integer ansPdfId = Integer.valueOf(request.getParameter("ansPdfId"));
		System.out.println("answerId to generate pdf" + ansPdfId);

		Answer answerObjPDF = answerDAO.displayAnswersById(ansPdfId);
		View view = new MyPdfView(answerObjPDF);

		return view;

	}

}
