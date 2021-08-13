<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="container-fluid">

    <div class="text-center">
        <p class="lead text-gray-800 mb-5"><spring:message code="error.message"/></p>
        <p class="text-gray-500 mb-0"><spring:message code="error.try_again"/></p>
    </div>

</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>