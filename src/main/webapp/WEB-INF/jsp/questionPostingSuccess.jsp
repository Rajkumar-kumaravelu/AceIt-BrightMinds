
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question Posting Success</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>


    </head>
    <body>
        <h1><c:out value="Welcome ${sessionScope.loggedInStudentUserName}"/></h1>
       <h1><c:out value="Your Id is: ${sessionScope.loggedInStudentId}"/></h1>
        <h1>Your Question is successfully posted.</h1>

        <!--<a href="studentQuestionPostingPage.htm" class="link-danger link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Click here to Post another Question</a>-->

        <form action="returnStudentDashboardPage.htm" method="POST">

            <br><input class="btn btn-primary" type="submit" value="Return to Dashboard">
        </form>

    </body>
</html>