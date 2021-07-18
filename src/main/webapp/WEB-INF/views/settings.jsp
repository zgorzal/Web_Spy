<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl-PL">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><spring:message code="app.title"/></title>
    <link href="../../theme/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="../../theme/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="#">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"><spring:message code="app.title"/></div>
        </a>
        <hr class="sidebar-divider my-0">
        <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span><spring:message code="dashboard.main_view"/></span></a>
        </li>
        <hr class="sidebar-divider">
        <div class="sidebar-heading">
            <spring:message code="dashboard.shortcuts"/>
        </div>
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
               aria-expanded="false"
               aria-controls="collapsePages">
                <i class="fas fa-fw fa-folder"></i>
                <span><spring:message code="dashboard.pages"/></span>
            </a>
            <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <h6 class="collapse-header"><spring:message code="dashboard.actions"/></h6>
                    <a class="collapse-item" href="#"> <spring:message code="dashboard.add_page"/></a>
                    <a class="collapse-item" href="#"> <spring:message code="dashboard.collecting_data"/></a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header"><spring:message code="dashboard.collected_data"/></h6>
                    <a class="collapse-item" href="#"> <spring:message code="dashboard.page_list"/></a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-fw fa-table"></i>
                <span> <spring:message code="dashboard.reports"/></span></a>
        </li>
        <sec:authorize access="hasRole('ADMIN')">
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                   aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-wrench"></i>
                    <span> <spring:message code="dashboard.admin_panel"/></span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header"><spring:message code="dashboard.action"/></h6>
                        <a class="collapse-item" href="#"> <spring:message code="dashboard.users_list"/></a>
                        <a class="collapse-item" href="#"> <spring:message code="dashboard.add_user"/></a>
                    </div>
                </div>
            </li>
        </sec:authorize>
        <hr class="sidebar-divider d-none d-md-block">
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>
                <ul class="navbar-nav ml-auto">
                    <div class="topbar-divider d-none d-sm-block"></div>
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.firstName} ${user.lastName}</span>
                            <img class="img-profile rounded-circle"
                                 src="../../theme/img/undraw_profile.svg">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                <spring:message code="dashboard.my_account"/>
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                <spring:message code="dashboard.settings"/>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                <spring:message code="dashboard.logout"/>
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-5 mb-4">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><spring:message
                                        code="settings.change_data"/></h6>
                            </div>
                            <div class="card-body">
                                <form:form method="post" modelAttribute="user">
                                    <div class="row-cols-1">
                                        <label>
                                            <form:input path="firstName" type="text" class="form-control"
                                                        placeholder="Imię"/>
                                            <form:errors path="firstName" class="alert-warning"/>
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <label>
                                            <form:input path="lastName" type="text" class="form-control"
                                                        placeholder="Nazwisko"/>
                                            <form:errors path="lastName" class="alert-warning"/>
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <label>
                                            <form:input path="email" type="email" class="form-control"
                                                        placeholder="Email"/>
                                            <form:errors path="email" class="alert-warning"/>
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <input type="submit" class="btn btn-success text"
                                               value="<spring:message code="settings.change_approves"/>">
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 mb-4">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><spring:message
                                        code="settings.change_password"/></h6>
                            </div>
                            <div class="card-body">
                                <form:form method="post" modelAttribute="user" action="/user/settings/password">
                                    <div class="row-cols-1">
                                        <label>
                                            <input type="password" name="oldPassword" class="form-control"
                                                   placeholder="Stare hasło">
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <label>
                                            <input type="password" name="newPassword" class="form-control"
                                                   placeholder="Nowe hasło">
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <label>
                                            <input type="password" name="repeatNewPassword" class="form-control"
                                                   placeholder="Powtórz nowe hasło">
                                        </label>
                                    </div>
                                    <div class="row-cols-1">
                                        <input type="submit" class="btn btn-warning text"
                                               value="<spring:message code="settings.change_password_approves"/>">
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span> <spring:message code="app.footer_text"/></span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><spring:message code="logout.sure"/></h5>
            </div>
            <div class="modal-body"><spring:message code="logout.message"/></div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal"><spring:message
                        code="app.cancel"/></button>
                <form action="<c:url value="/logout"/>" method="post">
                    <input class="btn btn-primary" type="submit" value="<spring:message code="dashboard.logout"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../../theme/vendor/jquery/jquery.min.js"></script>
<script src="../../theme/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../theme/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../../theme/js/sb-admin-2.min.js"></script>
</body>
</html>