package com.aceit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.aceit.DAO.*;
import com.aceit.model.*;
import com.aceit.validator.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegisterController {
	
	@Autowired
    StudentRegistrationValidator studentRegistrationValidator;
    
    @Autowired
    TutorRegistrationValidator tutorRegistrationValidator;
    
    @Autowired
    UserValidator userValidator;
    

    public RegisterController() {
    }
    
    @GetMapping(value = "/registerPage.htm")
    public ModelAndView registerPageRedirection(HttpServletRequest request) throws Exception {
        
        String selection = request.getParameter("selection");
        if (selection.equalsIgnoreCase("student")){
           
            request.setAttribute("student", new Student());
            request.setAttribute("user", new User());

            
            return new ModelAndView("studentRegisterPage");

        } 
        if (selection.equalsIgnoreCase("tutor")){
            
            request.setAttribute("tutor", new Tutor());
            request.setAttribute("user", new User());
            return new ModelAndView("tutorRegisterPage");
            
        } 
        return null;
    }
    

    @PostMapping(value="/studentRegistration.htm")
    public ModelAndView studentRegistration(@ModelAttribute("student") Student student, BindingResult result, SessionStatus status, Login login, StudentDAO studentDAO, User user, UserDAO userDAO){
        
        user.setUserName(student.getUserName());
        user.setPassword(student.getPassword());
        user.setRole(Roles.Student);
        
        studentRegistrationValidator.validate(student, result);
        userValidator.validate(user, result);
        
        System.out.println("StudentUserName:=" + student.getUserName());
   
        if(result.hasErrors())
            return new ModelAndView("studentRegisterPage");
        
        status.setComplete();
        student.setRole(Roles.Student);
        //user.setStudent(student);
        System.out.println("The set role using enum is:"+ student.getRole());
        System.out.println("The UserName in user:"+ user.getUserName());
        
        userDAO.registerUserDetails(user);
        System.out.println( user.getUserName()+": Registered in userTable procedding to register in student table");
        studentDAO.registerStudentDetails(student);
        System.out.println("Registration success in student table");
        return new ModelAndView("registrationSuccess"); 
    }
    

    @PostMapping(value="/tutorRegistration.htm")
   public ModelAndView tutorRegistration(@ModelAttribute("tutor") Tutor tutor, BindingResult result, SessionStatus status, Login login, TutorDAO tutorDAO, User user, UserDAO userDAO){
       
       user.setUserName(tutor.getUserName());
       user.setPassword(tutor.getPassword());
       user.setRole(Roles.Tutor);
       
       tutorRegistrationValidator.validate(tutor, result);
       userValidator.validate(user, result);
       
       System.out.println("StudentfirstName:=" + tutor.getSubject());
  
       if(result.hasErrors())
           return new ModelAndView("tutorRegisterPage");
       
       status.setComplete();
       tutor.setRole(Roles.Tutor);
       //user.setTutor(tutor);
       System.out.println("The set role using enum is:"+ tutor.getRole());
       
       userDAO.registerUserDetails(user);
       System.out.println( user.getUserName()+": Registered in userTable procedding to register in Tutor table");
       tutorDAO.registerTutorDetails(tutor);
       System.out.println("Registration success");
       return new ModelAndView("registrationSuccess"); 
   }
   
   
   @GetMapping(value="/loginPageRedirection.htm")
   public ModelAndView loginPageRedirection(Login login){
       return new ModelAndView("loginPage");
   }
   

}
