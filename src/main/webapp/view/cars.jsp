<%@ include file="components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="components/header.jsp" %>
    <link rel="stylesheet" href="/css/cars.css"/>
</head>
<script>
    var scrollPos = 0;
    window.addEventListener('scroll', function () {
        scrollPos = window.scrollY;
    });

    setTimeout(function () {
        location.reload();

        window.scrollTo(0, scrollPos);
    }, 10000);
</script>

<body>
<!-- Navbar -->
<%@ include file="components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container pt-5">
        <div class="d-flex">
            <!-- Sidebar -->
            <aside class="sidebar-car me-sm-4 pt-3">
                <ul class="fw-semibold">
                    <li class="ms-1">
                        <a href="<%= request.getContextPath() %>/cars"><i class="fa-solid fa-car"></i> Lista masini</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/user/post-car">
                            <button class="btn btn-warning">Vinde o masina</button>
                        </a>
                    </li>
                    <li>
                        <p class="ms-1"><i class="fa-solid fa-dollar-sign"></i> Interval pret </p>
                        <form action="<%= request.getContextPath() %>/cars">
                            <input class="form-control mb-3 ps-4 pe-0" type="number" name="low" required
                                   placeholder="Pret minim"/>
                            <input class="form-control mb-3 ps-4 pe-0" type="number" name="high" required
                                   placeholder="Pret maxim"/>
                            <button type="submit" class="btn btn-secondary">Cauta</button>
                        </form>
                    </li>
                </ul>
            </aside>

            <!-- Car List -->
            <div class="car-list">
                <!-- Search -->
                <form action="<%= request.getContextPath() %>/cars" id="searchForm" class="d-flex">
                    <input class="form-control" type="text" name="keyword" required
                           placeholder="Cauta dupa Marca/ Model/ An_Fabricatie"/>
                    <button type="submit" class="btn btn-light">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </form>
                <!-- List -->
                <div class="mt-4 row">
                    <c:if test="${!listCar.isEmpty()}">
                        <c:forEach items="${listCar}" var="car">
                            <c:if test="${car.status.equals('ACTIV')}">
                                <div class="col-12 col-md-6 col-md-4 col-lg-3 mb-3">
                                    <div class="card">
                                        <img class="card-img-top"
                                             src="data:${car.carPicture.fileType};base64,${car.carPicture.image}"
                                             alt="${car.make}"/>
                                        <div class="card-body">
                                            <p class="car-details fw-bold">${car.make} ${car.model} ${car.year}</p>
                                            <p class="car-price">$${car.price}</p>
                                            <a href="<%= request.getContextPath() %>/cars/${car.make}/${car.model}/${car.year}/${car.idCar}">
                                                <button class="btn btn-primary">Vizualizare masina</button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${listCar.isEmpty()}">
                        <h4 class="fw-bold text-secondary text-center">Masina nu a fost gasita</h4>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="components/footer.jsp" %>
</body>
</html>
