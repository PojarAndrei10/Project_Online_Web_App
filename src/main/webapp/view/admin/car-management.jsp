<%@ include file="../components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../components/header.jsp" %>
    <link rel="stylesheet" href="/css/admin.css"/>
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
<%@ include file="../components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container pt-5">
        <div class="d-flex">
            <!-- Sidebar -->
            <aside class="sidebar-admin pe-md-3">
                <ul>
                    <li>
                        <a href="<%= request.getContextPath() %>/admin"><i class="fa-solid fa-gauge-high"></i> Persoane
                        </a>
                    </li>
                    <li class="active-page">
                        <a href="<%= request.getContextPath() %>/admin/car-management"><i class="fa-solid fa-car"></i>
                            Masini </a>
                    </li>
                </ul>
            </aside>

            <!-- Content -->
            <div class="content-wrapper">
                <!-- List Car -->
                <h2 class="fw-bold mb-3">Lista Masini</h2>
                <div class="table-responsive-md">
                    <table class="table table-striped">
                        <!-- Head -->
                        <thead>
                        <tr>
                            <th>ID masina</th>
                            <th>Marca</th>
                            <th>Model</th>
                            <th>An</th>
                            <th>Pret initial</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        </thead>
                        <!-- Body -->
                        <tbody>
                        <c:forEach items="${listCar}" var="car">
                            <tr>
                                <td>${car.idCar}</td>
                                <td>${car.make}</td>
                                <td>${car.model}</td>
                                <td>${car.year}</td>
                                <td>${car.price}</td>
                                <c:if test="${car.status.equals('ACTIV')}">
                                    <td class="fw-semibold text-primary">${car.status}</td>
                                </c:if>
                                <c:if test="${car.status.equals('DEZACTIVAT')}">
                                    <td class="fw-semibold text-danger">${car.status}</td>
                                </c:if>
                                <c:if test="${car.status.equals('VANDUT')}">
                                    <td class="fw-semibold text-success">${car.status}</td>
                                </c:if>
                                <c:if test="${!car.status.equals('VANDUT')}">
                                    <td>
                                        <div class="dropdown">
                                            <button class="btn btn-warning dropdown-toggle" type="button"
                                                    data-bs-toggle="dropdown" aria-expanded="false"><i
                                                    class="fa-regular fa-pen-to-square"></i></button>
                                            <ul class="dropdown-menu dropdown-menu-dark">
                                                <li>
                                                    <a class="dropdown-item"
                                                       href="<%= request.getContextPath() %>/cars/${car.make}/${car.model}/${car.year}/${car.idCar}">Vizualizare
                                                        anunt</a>
                                                </li>
                                                <c:if test="${car.status.equals('ACTIV')}">
                                                    <li>
                                                        <a class="dropdown-item"
                                                           href="<%= request.getContextPath() %>/admin/deactivate/${car.idCar}">Dezactiveaza
                                                            postarea</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${car.status.equals('DEZACTIVAT')}">
                                                    <li>
                                                        <a class="dropdown-item"
                                                           href="<%= request.getContextPath() %>/admin/activate/${car.idCar}">Activeaza
                                                            postarea</a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </div>
                                    </td>
                                </c:if>
                                <c:if test="${car.status.equals('VANDUT')}">
                                    <td></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Car Bid -->
                <h2 class="fw-bold mb-3 mt-5">Oferta auto</h2>
                <div class="table-responsive-md">
                    <table class="table table-striped">
                        <!-- Head -->
                        <thead>
                        <tr>
                            <th>ID masina</th>
                            <th>Marca</th>
                            <th>Model</th>
                            <th>An</th>
                            <th>Licitatia</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        </thead>
                        <!-- Body -->
                        <tbody>
                        <c:forEach items="${listCarBid}" var="bid">
                            <tr>
                                <td>${bid.car.idCar}</td>
                                <td>${bid.car.make}</td>
                                <td>${bid.car.model}</td>
                                <td>${bid.car.year}</td>
                                <td>${bid.bidPrice}</td>
                                <c:if test="${bid.status.equals('IN_DESFASURARE')}">
                                    <td class="fw-semibold text-primary">${bid.status}</td>
                                </c:if>
                                <c:if test="${bid.status.equals('APROBAT')}">
                                    <td class="fw-semibold text-success">${bid.status}</td>
                                </c:if>
                                <c:if test="${bid.status.equals('REFUZAT')}">
                                    <td class="fw-semibold text-danger">${bid.status}</td>
                                </c:if>
                                <td>
                                    <c:if test="${bid.status.equals('IN_DESFASURARE')}">
                                        <div class="dropdown">
                                            <button class="btn btn-warning dropdown-toggle" type="button"
                                                    data-bs-toggle="dropdown" aria-expanded="false"><i
                                                    class="fa-regular fa-pen-to-square"></i></button>
                                            <ul class="dropdown-menu dropdown-menu-dark">
                                                <li>
                                                    <a class="dropdown-item"
                                                       href="<%= request.getContextPath() %>/cars/${bid.car.make}/${bid.car.model}/${bid.car.year}/${bid.car.idCar}">Vizualizare
                                                        anunt</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item"
                                                       href="<%= request.getContextPath() %>/admin/approve-bid/${bid.idBid}">Accepta
                                                        oferta</a>
                                                </li>
                                                <li>
                                                    <a class="dropdown-item"
                                                       href="<%= request.getContextPath() %>/admin/deny-bid/${bid.idBid}">Refuza
                                                        oferta</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- List Admin -->
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>
