<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="selectPage.select_page"/></h6>
    </div>
    <div class="card-body">
        <c:forEach items="${pages}" var="page">
            <a class="dropdown-item" href="#">
                <i class="fas fa-sm fa-fw mr-2 text-gray-400"></i>
                <p>${page.name}</p>
            </a>
            <hr class="sidebar-divider">
        </c:forEach>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>