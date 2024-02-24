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
        <div class="form-wrapper small">
            <h2 class="form-header">Actualizeaza fotografia</h2>
            <!-- FORM -->
            <form action="uploadPicture" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <label class="form-label">Fisier</label>
                <input class="form-control" type="file" name="imageFile"/>
                <p class="text-secondary">Imagini suportate : .jpg .jpeg .png</p>
                <p class="error">${message}</p>

                <button class="btn btn-primary form-button mt-3" type="submit">Actualizeaza</button>
            </form>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>
