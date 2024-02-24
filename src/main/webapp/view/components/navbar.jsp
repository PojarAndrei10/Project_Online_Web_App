<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg sticky-top p-0">
    <div class="container">
        <a class="navbar-brand fw-bold fs-5" href="<%= request.getContextPath() %>/">masiniFA</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto me-0 me-lg-auto fw-semibold">
                <li class="nav-item me-0 me-lg-3">
                    <a class="nav-link" href="<%= request.getContextPath() %>/">Acasa</a>
                </li>
                <li class="nav-item me-0 me-lg-3">
                    <a class="nav-link" href="<%= request.getContextPath() %>/cars">Lista Masini</a>
                </li>
                <li class="nav-item me-0 me-lg-3">
                    <a class="nav-link" href="<%= request.getContextPath() %>/user/post-car">Vinde o masina</a>
                </li>
            </ul>

            <!-- Login & Logout -->
            <security:authorize access="!isAuthenticated()">
                <div class="nav-button d-flex flex-column flex-lg-row">
                    <a href="<%= request.getContextPath() %>/login"
                       class="btn btn-outline-secondary me-0 me-lg-3 mb-3 mb-lg-0">Logare</a>
                    <a href="<%= request.getContextPath() %>/register"
                       class="btn btn-primary mb-3 mb-lg-0">Inregistrare</a>
                </div>
            </security:authorize>

            <!-- User Menu -->
            <security:authorize access="isAuthenticated()">
                <ul class="navbar-nav fw-semibold">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <div class="nav-account me-2">
                                <!-- Profile Picture -->
                                <c:if test="${profileLog.profilePicture == null}">
                                    <img src="/images/user/user-default.png" alt="Profile Picture"/>
                                </c:if>
                                <c:if test="${profileLog.profilePicture != null}">
                                    <img src="data:${profileLog.profilePicture.fileType};base64,${profileLog.profilePicture.image}"
                                         alt="Profile Picture"/>
                                </c:if>
                            </div>
                                ${profileLog.firstName}
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/user"><i
                                        class="fa-solid fa-user"></i> Profil</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/user/my-posted-car"><i
                                        class="fa-solid fa-car"></i> Masinile mele postate</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/user/test-drive"><i
                                        class="fa-regular fa-calendar-check"></i> Programare testDrive</a>
                            </li>
                            <!-- Admin Dashboard -->
                            <security:authorize access="hasRole('ADMIN')">
                                <li>
                                    <a class="dropdown-item" href="<%= request.getContextPath() %>/admin/dashboard"><i
                                            class="fa-solid fa-gauge-high"></i> General </a>
                                </li>
                            </security:authorize>

                            <li class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item" href="<%= request.getContextPath() %>/logout">
                                    <button class="nav-logout btn btn-primary">Delogare</button>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </security:authorize>
        </div>
    </div>
</nav>
