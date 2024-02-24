<%@ include file="components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="components/header.jsp" %>
    <link rel="stylesheet" href="/css/form.css"/>
</head>
<body>
<!-- Include Navbar -->
<%@ include file="components/navbar.jsp" %>

<!-- Main Content -->
<main>
    <div class="container d-flex justify-content-center">
        <div class="form-wrapper small">
            <h2 class="form-header">Logare</h2>
            <%--@elvariable id="user" type=""--%>
            <form:form action="loginUser" method="POST" modelAttribute="user">
                <c:if test="${param.error != null}">
                    <div class="login-error">
                        <p>Invalid user sau parola</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="login-logout">
                        <p>Ati fost deconectat cu succes</p>
                    </div>
                </c:if>
                <label class="form-label fs-6">User</label>
                <form:input id="username" class="form-control" type="text" path="username"/>

                <label class="form-label fs-6">Parola</label>
                <form:input id="password" class="form-control" type="password" path="password"/>

                <button class="btn btn-primary mt-2 form-button" type="submit">Logare</button>

                <div class="mt-3 text-center">
                    <a href="<%= request.getContextPath() %>/register" class="text-decoration-none">
                        Creati un cont</a>
                </div>
            </form:form>
        </div>
    </div>
</main>
<!-- Include Footer -->
<%@ include file="components/footer.jsp" %>
</body>
</html>
