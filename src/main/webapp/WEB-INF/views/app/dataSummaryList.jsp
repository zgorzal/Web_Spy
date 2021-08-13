<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">${cssClass}</h6>
    </div>
    <div class="card-body">
        <p><spring:message code="select_data"/></p>
        <c:forEach items="${records}" var="record">
            <a href="/page/report/${record.downloadId}">${record.dateAdded}</a><br>
        </c:forEach>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>