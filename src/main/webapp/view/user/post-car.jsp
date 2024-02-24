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
            <h2 class="form-header">Vinde o masina</h2>
            <!-- FORM -->
            <%--@elvariable id="car" type=""--%>
            <form:form action="postCarProcess" method="POST" enctype="multipart/form-data" modelAttribute="car">
                <label class="form-label">Marca</label>
                <div>
                    <form:errors path="make" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="make" cssErrorClass="form-control error-border"/>

                <label class="form-label">Model</label>
                <div>
                    <form:errors path="model" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="model" cssErrorClass="form-control error-border"/>

                <label class="form-label">An</label>
                <div>
                    <form:errors path="year" cssClass="error"/>
                </div>
                <form:input class="form-control" type="text" path="year" cssErrorClass="form-control error-border"/>

                <label class="form-label">Pret</label>
                <div>
                    <form:errors path="price" cssClass="error"/>
                </div>
                <form:input class="form-control" type="number" path="price" cssErrorClass="form-control error-border"/>

                <label class="form-label">Poza cu masina</label>
                <div>
                    <span class="error">${fileError}</span>
                </div>
                <input class="form-control" type="file" name="imageFile"/>

                <button class="btn btn-primary form-button mt-3" type="submit">Posteaza Anunt</button>
            </form:form>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>
