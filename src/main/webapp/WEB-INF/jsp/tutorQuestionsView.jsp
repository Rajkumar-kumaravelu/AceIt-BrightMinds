<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tutor Dashboard Page</title>
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

        <div align="center">
            <h3>Your Questions for Answering</h3>
            <table border="1px solid black" cellpadding="20">

                <tr>
                    <th>Question</th>
                    <th>Comments</th>
                    <th>Subject</th>
                    <th>Actions</th>
                    
                </tr>


                <c:forEach var="ques" items="${listallQuestion}">
                    <tr>
                        <form:form action="tutorAnswerPostingPage.htm" method="POST" modelAttribute="question">

                            <td>${ques.question}</td>
                            <td>${ques.comments}</td>
                            <td>${ques.subject}</td>
                           
                            
                            <form:hidden path="question" value="${ques.question}"></form:hidden>
                            <form:hidden path="questionId" value="${ques.questionId}"></form:hidden>
                            <form:hidden path="comments" value="${ques.comments}"></form:hidden>
                            <form:hidden path="subject" value="${ques.subject}"></form:hidden>

                            <td><form:button class="btn btn-primary" type="submit" value="Answer">Answer</form:button></td>
                            
                        </form:form>
                    </tr>
                </c:forEach>
            </table>
            
            <form action="returnTutorDashboardPage.htm" method="POST">

                <br><input class="btn btn-primary" type="submit" value="Return to Dashboard">
            </form>
        </div>	

    </body>
</html>
