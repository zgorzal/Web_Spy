<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <a class="nav-link" href="/">
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
                    <a class="collapse-item" href="/page/add"> <spring:message code="dashboard.add_page"/></a>
                    <a class="collapse-item" href="/page"> <spring:message code="dashboard.collecting_data"/></a>
                    <div class="collapse-divider"></div>
                    <h6 class="collapse-header"><spring:message code="dashboard.collected_data"/></h6>
                    <a class="collapse-item" href="/page/list"> <spring:message code="dashboard.page_list"/></a>
                </div>
            </div>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/page/reports">
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
                        <a class="collapse-item" href="/user/list"> <spring:message code="dashboard.users_list"/></a>
                        <a class="collapse-item" href="/logs"> <spring:message code="dashboard.check_logs"/></a>
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
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                <sec:authorize access="isAuthenticated()">
                                    <sec:authentication property="principal.user.firstName"/>
                                    <sec:authentication property="principal.user.lastName"/>
                                </sec:authorize>
                            </span>
                            <img class="img-profile rounded-circle"
                                 src="../../theme/img/undraw_profile.svg">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="/user/account">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                <spring:message code="dashboard.my_account"/>
                            </a>
                            <a class="dropdown-item" href="/user/settings">
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