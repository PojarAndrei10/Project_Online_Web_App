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
                    <li class="active-page">
                        <a href="<%= request.getContextPath() %>/admin"><i class="fa-solid fa-gauge-high"></i> Persoane</a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/admin/car-management"><i class="fa-solid fa-car"></i>
                            Masini</a>
                    </li>
                </ul>
            </aside>

            <!-- Content -->
            <div class="content-wrapper">
                <!-- List User -->
                <h2 class="fw-bold mb-3">Utilizatori</h2>
                <div class="table-responsive-md">
                    <table class="table table-striped">
                        <!-- Head -->
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Prenume</th>
                            <th>Nume de familie</th>
                            <th>Numar de telefon</th>
                            <th>Adresa</th>
                            <th></th>
                        </tr>
                        </thead>
                        <!-- Body -->
                        <tbody>
                        <c:forEach items="${listUser}" var="user">
                            <tr>
                                <td>${user.idUser}</td>
                                <td>${user.profile.firstName}</td>
                                <td>${user.profile.lastName}</td>
                                <td>${user.profile.phoneNumber}</td>
                                <td>${user.profile.address}</td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-warning dropdown-toggle" type="button"
                                                data-bs-toggle="dropdown" aria-expanded="false"><i
                                                class="fa-regular fa-pen-to-square"></i></button>
                                        <ul class="dropdown-menu dropdown-menu-dark">
                                            <li>
                                                <a class="dropdown-item"
                                                   href="<%= request.getContextPath() %>/admin/edit-user?id=${user.profile.idProfile}">Editeaza
                                                    profilul</a>
                                            </li>
                                            <li>
                                                <a class="dropdown-item"
                                                   href="<%= request.getContextPath() %>/admin/mark-admin/${user.idUser}">"Transforma"
                                                    in Admin</a>
                                            </li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- List Admin -->
                <h2 class="fw-bold mb-3 mt-5">Admin</h2>
                <div class="table-responsive-md">
                    <table class="table table-striped">
                        <!-- Head -->
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Prenume</th>
                            <th>Nume de familie</th>
                            <th>Numar de telefon</th>
                            <th>Adresa</th>
                            <th></th>
                        </tr>
                        </thead>
                        <!-- Body -->
                        <tbody>
                        <c:forEach items="${listAdmin}" var="admin">
                            <tr>
                                <td>${admin.idUser}</td>
                                <td>${admin.profile.firstName}</td>
                                <td>${admin.profile.lastName}</td>
                                <td>${admin.profile.phoneNumber}</td>
                                <td>${admin.profile.address}</td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-warning dropdown-toggle" type="button"
                                                data-bs-toggle="dropdown" aria-expanded="false"><i
                                                class="fa-regular fa-pen-to-square"></i></button>
                                        <ul class="dropdown-menu dropdown-menu-dark">
                                            <li>
                                                <a class="dropdown-item"
                                                   href="<%= request.getContextPath() %>/admin/edit-user?id=${admin.profile.idProfile}">Editeaza
                                                    profilul</a>
                                            </li>
                                        </ul>
                                    </div>
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
