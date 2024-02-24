<%@ include file="../components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../components/header.jsp" %>
    <link rel="stylesheet" href="/css/form.css"/>
</head>
<body>
<!-- Navbar -->
<%@ include file="../components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container d-flex justify-content-center">
        <div class="form-wrapper medium">
            <h2 class="form-header">Editeaza profilul</h2>
            <!-- FORM -->
            <%--@elvariable id="profile" type=""--%>
            <form:form action="editProfileProcess" method="POST" modelAttribute="profile">
                <form:hidden path="idProfile"/>

                <label class="form-label">Prenume</label>
                <div>
                    <form:errors path="firstName" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="firstName"
                            cssErrorClass="form-control error-border"/>

                <label class="form-label">Nume de familie</label>
                <div>
                    <form:errors path="lastName" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="lastName" cssErrorClass="form-control error-border"/>

                <label class="form-label">Numar de telefon</label>
                <div>
                    <form:errors path="phoneNumber" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="phoneNumber"
                            cssErrorClass="form-control error-border"/>

                <label class="form-label">Adresa</label>
                <div>
                    <form:errors path="address" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="address" cssErrorClass="form-control error-border"/>

                <label class="form-label">Despre</label>
                <form:input class="form-control" type="text" path="about"/>

                <button class="btn btn-primary form-button mt-3" type="submit">Salveaza modificarile</button>
            </form:form>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>
