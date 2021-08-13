<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="dashboard.my_account"/></h6>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="btn btn-primary btn-icon-split">
            <span class="icon text-white-50">
                <i class="fas fa-flag"></i>
            </span>
                <span class="text"><spring:message code="admin"/></span>
            </div>
        </sec:authorize>
    </div>
    <div class="card-body">
        <p><spring:message code="user_id"/> <sec:authentication property="principal.user.id"/></p>
        <p><spring:message code="user_first_name"/> <sec:authentication property="principal.user.firstName"/></p>
        <p><spring:message code="user_last_name"/> <sec:authentication property="principal.user.lastName"/></p>
        <p><spring:message code="user_email"/> <sec:authentication property="principal.user.email"/></p>
        <p><spring:message code="user_has_role"/> <sec:authentication property="authorities"/></p>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>