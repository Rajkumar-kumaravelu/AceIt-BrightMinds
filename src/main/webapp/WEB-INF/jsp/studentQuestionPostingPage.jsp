<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Student Question Post</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
                    rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
                    crossorigin="anonymous">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
                    crossorigin="anonymous"></script>

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

                <form:form action="questionSubmit.htm" method="POST" modelAttribute="question">
                    <div class="col-md-12">
                        <label class="form-label" for="subject">Select Subject:</label>
                        <form:select path="subject">
                            <form:option value="Computer Science" label="Computer Science" />
                            <form:option value="Physics" label="Physics" />
                            <form:option value="Mathematics" label="Mathematics" />
                            <form:option value="Chemistry" label="Chemistry" />
                        </form:select> <br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="question">Question</label>
                        <form:textarea class="form-control" path="question" rows="5" cols="30" />
                        <form:errors path="question" style="color:red;" /><br>
                    </div>
                    <div class="col-md-12">
                        <label class="form-label" for="comments">Comments</label>
                        <form:input class="form-control" path="comments" />

                    </div>


                    <br><input class="btn btn-primary" type="submit" value="PostQuestion">

                </form:form>

                <form action="returnStudentDashboardPage.htm" method="POST">

                    <br><input class="btn btn-primary" type="submit" value="Return to Dashboard">
                </form>
            </body>

            </html>