package com.aceit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aceit.validator.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.aceit.DAO.*;
import com.aceit.model.*;

@RestController
public class LoginController {

	@Autowired
	LoginValidator loginValidator;

	EmailValidator emailValidator = new EmailValidator();

	@GetMapping(value = "/loginPage.htm")
	public ModelAndView LoginPageRedirection(HttpServletRequest request) throws Exception {
		request.setAttribute("login", new Login());
		return new ModelAndView("loginPage");
	}

	@GetMapping("/about.htm")
	public ModelAndView aboutUs(HttpServletRequest request) {
		
		return new ModelAndView("about");
	}

	@PostMapping(value = "/loginPage.htm")
	public ModelAndView userPage(@ModelAttribute("login") Login login, BindingResult result, SessionStatus status,
			HttpServletRequest request, HttpServletResponse response, UserDAO userDAO, User user, StudentDAO studentDAO, TutorDAO tutorDAO)
			throws Exception {

		try {

			System.out.println("email:=" + login.getUserName());

			loginValidator.validate(login, result);

			if (result.hasErrors()) {
				return new ModelAndView("loginPage");
			}
			status.setComplete();
			
		    
			HttpSession session = request.getSession(true);
			
			String loggedInUser = login.getUserName();


			String loggedInUserRole = userDAO.getValidUserRole(login);

			System.out.println("???????The loggedInUserRole in login controller: " + loggedInUserRole);

			session.setAttribute("loggedInUser", loggedInUser);
			session.setAttribute("loggedInUserRole", loggedInUserRole);

			if (session.getAttribute("loggedInUser") != null && session.getAttribute("loggedInUserRole") != null ) {

				if (loggedInUserRole.equalsIgnoreCase("student")) {

					Student loggedInStudentObj = studentDAO.getLoggedInStudentObj(login);

					int loggedInStudentId = loggedInStudentObj.getStudentId();
					String loggedInStudentUserName = loggedInStudentObj.getUserName();

					System.out.println("studentId inside student if loop:=" + loggedInStudentId);
					System.out.println("studentUserName inside student if loop:=" + loggedInStudentUserName);

					session.setAttribute("loggedInStudentObj", loggedInStudentObj);
					session.setAttribute("loggedInStudentUserName", loggedInStudentUserName);
					session.setAttribute("loggedInStudentId", loggedInStudentId);

					// request.setAttribute("question", new Question());

					return new ModelAndView("studentDashboardPage");
				}
				if (loggedInUserRole.equalsIgnoreCase("tutor")) {

					Tutor loggedInTutorObj = tutorDAO.getLoggedInTutorObj(login);

					int loggedInTutorId = loggedInTutorObj.getTutorId();
					String loggedInTutorUserName = loggedInTutorObj.getUserName();
					String loggedInTutorSubject = loggedInTutorObj.getSubject();

					session.setAttribute("loggedInTutorObj", loggedInTutorObj);
					session.setAttribute("loggedInTutorUserName", loggedInTutorUserName);
					session.setAttribute("loggedInTutorId", loggedInTutorId);
					session.setAttribute("loggedInTutorSubject", loggedInTutorSubject);

					return new ModelAndView("tutorDashboardPage");
				}

			}
			if (session.getAttribute("loggedInUser") == null && session.getAttribute("loggedInUserRole") == null ) {
				return new ModelAndView("loginPage");
			}
			

		} catch (Exception e) {

			System.out.println("Exception: " + e.getMessage());

		}
		return null;
	}
	
	
	@GetMapping("/logout.htm")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response, Login login) {
		HttpSession session = request.getSession(false);
		
		if (session != null) {
	        session.invalidate();
	    }

		return new ModelAndView("loginPage");
	}
	
}
	
	

