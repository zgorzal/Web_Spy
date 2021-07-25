<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<c:forEach items="${pages}" var="page">
    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h6 class="m-0 font-weight-bold text-primary">${page.name}</h6>
        </div>
        <div class="card-body">
            <c:forEach items="${page.css}" var="css">
                <a href="reports/${css.name}">${css.name}</a><br>
            </c:forEach>
        </div>
    </div>
</c:forEach>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>