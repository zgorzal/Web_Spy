<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="dashboard.logs"/></h6>
    </div>
    <div class="card-body">
        <c:forEach items="${logs}" var="log">
            <p>${log}</p>
        </c:forEach>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>