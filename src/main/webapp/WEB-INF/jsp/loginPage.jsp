<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Form</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>

    </head>
    <body>

      <!--<div class="container min-vh-100 d-md-flex justify-content-center align-items-center">-->
      
        <div class="container min-vh-100 d-flex flex-row bd-highlight mb-1 justify-content-center align-items-center">
            
                
                <form:form action="loginPage.htm" method="POST" modelAttribute="login">
                    <div class="col-md-12">
                        <label class="form-label" for="email">User Name</label>
                        <form:input path="userName" class="form-control"/> 
                        <form:errors path="userName" style="color:red;"/><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="password">Password:</label>
                        <form:password path="password" class="form-control"/> 
                        <form:errors path="password" style="color:red;"/><br>
                    </div>
                    <input class="btn btn-primary" type="submit" value="Login">
                      
                </form:form>
            
            
            <div class="p-5 bd-highlight">
                <br><a href="about.htm">Click to know about Us</a><br> 

                <div class="btn-group">
                    <br> <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Don't have an account? Register as
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item-primary" href="registerPage.htm?selection=student">Student</a></li>
                        <li><a class="dropdown-item-primary" href="registerPage.htm?selection=tutor">Tutor</a></li>
                    </ul>
                </div>
            </div>



            <!--            <form action="registerPage.htm" method="post">
                            <br>  <span>Don't have an account? Register as</span> <br>
            
                            <div class="col-md-6">
                                <select name="selection"> Select
                                    <option>Student</option>
                                    <option>Tutor</option>   
                                </select> <br>
                            </div>  
                            <br> <input class="btn btn-primary" type="submit" value="Register">
            
                        </form>-->

        </div>
     

    </body>
</html>