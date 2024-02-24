<%@ include file="components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="components/header.jsp" %>
    <link rel="stylesheet" href="/css/about-us.css"/>
</head>
<body>
<!-- Navbar -->
<%@ include file="components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container-fluid jumbotron">
        <div class="container p-4">
            <h2 class="pt-5 fw-bolder">Despre masiniFA</h2>
        </div>
    </div>

    <div class="container mt-4 d-flex">
        <div class="about p-4">
            <h3 class="fw-bolder">Cine suntem?</h3>
            <p class="text-secondary">
                masiniFA este o piata digitala de top si un furnizor de solutii pentru industria auto, care ajuta
                vanzatorii prin a-si vinde masina instant,companiei noastre, si a
                primii banii pe loc,iar ulterior noi sa vindem masina,proprietatea noastra, sub forma de licitatie.
                Lansata in 2022 si cu sediul in
                CJ-N, Romania, compania ofera cumparatorilor
                datele, resursele si instrumentele digitale necesare pentru a lua decizii de cumparare informate,dar si
                posibilitatea de a-si programa un TestDrive.
            </p>
        </div>
        <img class="image-about" src="/images/background/about-us-02.jpg" alt=""/>
    </div>
</main>

<!-- Footer -->
<%@ include file="components/footer.jsp" %>
</body>
</html>
