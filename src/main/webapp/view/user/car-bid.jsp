<%@ include file="../components/taglib.jsp" %>
<!-- Includerea fișierului taglib.jsp pentru a adăuga librării suplimentare -->

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../components/header.jsp" %>
    <!-- Includerea fișierului header.jsp pentru a adăuga conținut în partea de sus a paginii -->
    <link rel="stylesheet" href="/css/form.css"/>
    <!-- Adăugarea unui fișier CSS pentru stilizarea formularului -->
</head>
<body>
<!-- Navbar -->
<%@ include file="../components/navbar.jsp" %>
<!-- Includerea fișierului navbar.jsp pentru a adăuga bara de navigare -->

<!-- Main -->
<main>
    <div class="container d-flex justify-content-center">
        <div class="form-wrapper small">
            <h2 class="form-header">Plaseaza o licitatie</h2>
            <!-- Titlul formularului -->

            <c:if test="${highestBidding == 0}">
                <!-- Verificare condițională JSTL: dacă cea mai mare licitație este 0 -->
                <p class="text-secondary fs-5 m-0">Pret pornire:</p>
                <!-- Afișarea prețului de pornire al automobilului -->
                <p class="fs-5 m-0">$${car.price}</p>
                <!-- Afișarea prețului într-un paragraf -->
            </c:if>

            <c:if test="${highestBidding != 0}">
                <!-- Verificare condițională JSTL: dacă cea mai mare licitație nu este 0 -->
                <p class="text-secondary fs-5 m-0 text-uppercase">Licitatia maxima actuala:</p>
                <!-- Afișarea licitației maxime curente -->
                <p class="fs-5 m-0">$${highestBidding}</p>
                <!-- Afișarea licitației maxime într-un paragraf -->
            </c:if>

            <!-- FORM -->
            <p class="error">${message}</p>
            <!-- Afișarea mesajului de eroare, dacă există vreunul -->

            <form:form action="postCarBidding" method="POST" modelAttribute="carBidding">
                <!-- Formularul JSP care trimite datele către "postCarBidding" prin metoda POST -->
                <form:hidden path="user" value="${user.idUser}"/>
                <!-- Adăugarea unui câmp ascuns pentru ID-ul utilizatorului -->

                <form:hidden path="car" value="${car.idCar}"/>
                <!-- Adăugarea unui câmp ascuns pentru ID-ul automobilului -->

                <input type="hidden" name="highestBidding" value="${highestBidding}"/>
                <!-- Adăugarea unui câmp ascuns pentru valoarea licitației maxime -->

                <label class="fs-6 form-label">Licitatia ta</label>
                <!-- Eticheta pentru câmpul de introducere a licitației -->

                <form:input class="form-control" type="number" path="bidPrice" cssErrorClass="error-border"/>
                <!-- Câmpul de introducere a licitației, cu posibilitatea afișării unei clase de eroare -->

                <form:errors path="bidPrice" cssClass="error"/>
                <!-- Afișarea erorilor asociate câmpului de licitație, dacă există vreuna -->

                <button class="btn btn-primary form-button" type="submit">Plaseaza licitatia</button>
                <!-- Butonul pentru trimiterea formularului de licitație -->
            </form:form>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
<!-- Includerea fișierului footer.jsp pentru a adăuga conținut în partea de jos a paginii -->
</body>
</html>