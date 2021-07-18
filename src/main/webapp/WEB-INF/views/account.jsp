<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Moje konto</h6>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="btn btn-primary btn-icon-split">
            <span class="icon text-white-50">
                <i class="fas fa-flag"></i>
            </span>
                <span class="text">Administrator</span>
            </div>
        </sec:authorize>
    </div>
    <div class="card-body">
        <p>Imię:</p>
        <p>Nazwisko:</p>
        <p>Email:</p>
        <p>Data założenia konta:</p>
        <p>Data ostatniego logowania:</p>
        <p>Ilość zapisanych stron:</p>
        <p>Ilość pobranych rekordów:</p>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>