<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Student Register Form</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>

    </head>
    <body>

        <h1>Student Registration</h1>
        
        <!--<div class="container d-md-flex justify-content-center align-items-center">-->
                    <div class="container min-vh-100 d-flex flex-row bd-highlight mb-1 justify-content-center align-items-center">

                <form:form action="studentRegistration.htm" method="POST" modelAttribute="student">
                    <div class="col-md-12">
                        <label class="form-label" for="firstname">First Name:</label>
                        <form:input class="form-control" path="firstName"/>
                        <form:errors path="firstName" style="color:red;"/><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="lastname">Last Name:</label>
                        <form:input class="form-control" path="lastName"/>
                        <form:errors path="lastName" style="color:red;"/><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="userName">Username</label>
                        <form:input class="form-control" path="userName"/>
                        <form:errors path="userName" style="color:red;"/><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="email">Email:</label>
                        <form:input class="form-control" path="email"/>
                        <form:errors path="email" style="color:red;"/><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="password">Password:</label>
                        <form:password class="form-control" path="password"/>
                        <form:errors path="password" style="color:red;"/><br>
                    </div>
                   <br><input class="btn btn-primary" type="submit" value="Register">
                    
                </form:form>
            
        </div>
    </body>
</html>