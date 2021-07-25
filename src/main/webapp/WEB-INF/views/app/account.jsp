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
        <p>ID: <sec:authentication property="principal.user.id"/></p>
        <p>Imię: <sec:authentication property="principal.user.firstName"/></p>
        <p>Nazwisko: <sec:authentication property="principal.user.lastName"/></p>
        <p>Email: <sec:authentication property="principal.user.email"/></p>
        <p>Posiadane role: <sec:authentication property="authorities"/></p>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>