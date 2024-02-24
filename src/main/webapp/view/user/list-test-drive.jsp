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
                    <li>
                        <a href="<%= request.getContextPath() %>/user/my-posted-car"><i class="fa-solid fa-car"></i>
                            Masinile mele postate</a>
                    </li>
                    <li class="active-page">
                        <a href="<%= request.getContextPath() %>/user/test-drive"><i
                                class="fa-regular fa-calendar-check"></i> Programare testDrive</a>
                    </li>

                </ul>
            </aside>

            <!-- Content -->
            <div class="content-wrapper">
                <h2 class="fw-bold mb-3">Lista cu programarile pentru Test Drive</h2>
                <!-- Table -->
                <c:if test="${!listTestDrive.isEmpty()}">
                    <div class="table-responsive-md">
                        <table class="table table-striped">
                            <!-- Head -->
                            <thead>
                            <tr>
                                <th>ID masina</th>
                                <th>Marca</th>
                                <th>Model</th>
                                <th>An</th>
                                <th>Client</th>
                                <th>Data</th>
                                <th></th>
                            </tr>
                            </thead>
                            <!-- Body -->
                            <tbody>
                            <c:forEach items="${listTestDrive}" var="test">
                                <tr>
                                    <td>${test.car.idCar}</td>
                                    <td>${test.car.make}</td>
                                    <td>${test.car.model}</td>
                                    <td>${test.car.year}</td>
                                    <td>${test.user.profile.firstName}</td>
                                    <td>${test.date}</td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

                <c:if test="${listTestDrive.isEmpty()}">
                    <p class="text-secondary">Nu aveti nici o cerere programata de Test Drive</p>
                </c:if>
            </div>
        </div>
    </div>
</main>

<!-- Footer -->
<%@ include file="../components/footer.jsp" %>
</body>
</html>