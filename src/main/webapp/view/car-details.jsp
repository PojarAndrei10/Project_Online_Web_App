<%@ include file="components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="components/header.jsp" %>
    <link rel="stylesheet" href="/css/cars.css"/>
</head>
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
                        <a href="<%= request.getContextPath() %>/cars"><i class="fa-solid fa-car"></i> Lista Masini </a>
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
                <!-- Car Details -->
                <div class="mt-4 row">
                    <!-- Image -->
                    <div class="car-image col-12 col-sm-8">
                        <img class="img-fluid" alt="${car.make}"
                             src="data:${car.carPicture.fileType};base64,${car.carPicture.image}"/>
                    </div>
                    <!-- Details -->
                    <div class="car-details col-12 col-sm-4">
                        <h4 class="fw-bold">${car.make} ${car.model} ${car.year}</h4>
                        <p class="text-secondary m-0">PRET PORNIRE:</p>
                        <p class="text-black fs-5">$${car.price}</p>
                        <c:if test="${highestBidding != 0}">
                            <p class="text-secondary m-0">LICITATIA MAXIMA:</p>
                            <p class="text-black fs-5">$${highestBidding}</p>
                        </c:if>
                        <c:if test="${highestBidding == 0}">
                            <p class="text-secondary m-0">LICITATIA MAXIMA:</p>
                            <p class="text-black fs-5">$${highestBidding}</p>
                        </c:if>
                        <div class="car-button-actions">
                            <a class="text-decoration-none"
                               href="<%= request.getContextPath() %>/car-bid?id=${car.idCar}">
                                <button class="btn btn-primary">Plaseaza o licitatie</button>
                            </a>
                            <a class="text-decoration-none"
                               href="<%= request.getContextPath() %>/test-drive/${car.idCar}">
                                <button class="btn btn-warning">Programeaza un Test Drive</button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="components/footer.jsp" %>
</body>
</html>

