<%@ include file="components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="components/header.jsp" %>
    <link rel="stylesheet" href="/css/form.css"/>
</head>
<body>
<!-- Navbar -->
<%@ include file="components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container d-flex justify-content-center">
        <div class="form-wrapper small">
            <h2 class="form-header">Inregistrare</h2>

            <%-- Verificare mesaj de eroare din model și afișare --%>
            <c:if test="${not empty message}">
                <div class="alert alert-danger">${message}</div>
            </c:if>

            <%--@elvariable id="account" type=""--%>
            <form:form action="accountProcess" method="POST" modelAttribute="account">
                <label class="form-label fs-6">User</label>
                <div>
                    <form:errors path="username" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="username" cssErrorClass="form-control error-border"/>

                <label class="form-label fs-6">Parola</label>
                <div>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <form:input class="form-control" type="password" path="password"
                            cssErrorClass="form-control error-border"/>

                <button class="btn btn-primary mt-3 form-button" type="submit">Inregistrare</button>
            </form:form>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="components/footer.jsp" %>
</body>
</html>
