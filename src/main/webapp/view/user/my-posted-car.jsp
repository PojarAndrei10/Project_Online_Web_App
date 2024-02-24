<%@ include file="../components/taglib.jsp" %>
<%@ include file="../components/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../components/header.jsp" %>
    <link rel="stylesheet" href="/css/user.css"/>
</head>
<body>
<!-- Navbar -->
<%@ include file="../components/navbar.jsp" %>

<!-- Main -->
<main>
    <div class="container pt-5">
        <div class="d-flex">
            <!-- Sidebar -->
            <aside class="sidebar-user pe-md-3">
                <ul>
                    <li>
                        <a href="<%= request.getContextPath() %>/user"><i class="fa-solid fa-user"></i> Profil</a>
                    </li>
                    <li class="active-page">
                        <a href="<%= request.getContextPath() %>/user/my-posted-car"><i class="fa-solid fa-car"></i>
                            Masinile mele postate</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/user/test-drive"><i
                                class="fa-regular fa-calendar-check"></i> Programare testDrive</a>
                    </li>
                </ul>
            </aside>

            <!-- Content -->
            <div class="content-wrapper">
                <h2 class="fw-bold mb-3">Masinile mele postate</h2>
                <!-- Table -->
                <c:if test="${!userCar.isEmpty()}">
                    <div class="table-responsive-md">
                        <table class="table table-striped">
                            <!-- Head -->
                            <thead>
                            <tr>
                                <th>ID masina</th>
                                <th>Marca</th>
                                <th>Model</th>
                                <th>An</th>
                                <th>Pret</th>
                                <th>Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <!-- Body -->
                            <tbody>
                            <c:forEach items="${userCar}" var="car">
                                <tr>
                                    <td>${car.idCar}</td>
                                    <td>${car.make}</td>
                                    <td>${car.model}</td>
                                    <td>${car.year}</td>
                                    <td>$${car.price}</td>
                                    <c:if test="${car.status.equals('ACTIV')}">
                                        <td class="fw-semibold text-primary">${car.status}</td>
                                    </c:if>
                                    <c:if test="${car.status.equals('DEZACTIVAT')}">
                                        <td class="fw-semibold text-danger">${car.status}</td>
                                    </c:if>
                                    <c:if test="${car.status.equals('VANDUT')}">
                                        <td class="fw-semibold text-success">${car.status}</td>
                                    </c:if>
                                    <c:if test="${car.status.equals('VANDUT')}">
                                        <td></td>
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
                                                           href="<%= request.getContextPath() %>/cars/${car.make}/${car.model}/${car.year}/${car.idCar}">Detalii
                                                            masina</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${userCar.isEmpty()}">
                    <p class="text-secondary">Nu ati postat inca nicio masina. Doriti sa va vindeti masina?</p>
                    <a href="<%= request.getContextPath() %>/user/post-car">
                        <button class="btn btn-primary">Vinde-o</button>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>
