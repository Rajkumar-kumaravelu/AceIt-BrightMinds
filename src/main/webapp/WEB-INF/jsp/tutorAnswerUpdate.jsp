<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Tutor Answer Update</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>

    </head>
    <body>
        <div>

            <h3>
                <c:out value="Welcome ${sessionScope.loggedInUser}" />
            </h3>
            <h3>
                <c:out value="You are Registered: ${sessionScope.loggedInUserRole}" />
            </h3>

        </div>

        <div>
            
            <form:form action="answerUpdateSubmit.htm" method="POST" modelAttribute="answer">
                <div class="col-md-12">
                    <form:hidden path = "answerId" value = "${displayAnswerById.answerId}" />
                    <h3>  Answer Id:${displayAnswerById.answerId}</h3>
                </div> 
                <div class="col-md-12">
                    <form:hidden path = "answeredQuestion" value = "${displayAnswerById.answeredQuestion}" />
                    <h3>  Question:${displayAnswerById.answeredQuestion}</h3>
                </div>
                <div class="col-md-12">
                    <label class="form-label" for="answer">Update your Answer</label>
                    <form:input class="form-control" path="answer" value="${displayAnswerById.answer}"/>
                    <form:errors path="answer" style="color:red;"/><br>
                </div>
                <div class="col-md-12">
                    <label class="form-label" for="ansComments">Update Your Comments</label>
                    <form:input class="form-control" path="ansComments" value="${displayAnswerById.ansComments}"/>
                </div>
                <br><input class="btn btn-primary" type="submit" value="Update Answer">
            </form:form>





        </div>	
    </body>
</html>
