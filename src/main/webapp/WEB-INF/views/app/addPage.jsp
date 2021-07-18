<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="addPage.add_page"/></h6>
    </div>
    <div class="card-body">
        <form:form method="post" modelAttribute="page">
            <div class="form-group">
                <form:input path="name" type="text" class="form-control"
                            placeholder="Nazwa"/>
                <form:errors path="name" class="alert-warning"/>
            </div>
            <div class="form-group">
                <form:input path="url" type="text" class="form-control"
                            placeholder="Adres URL"/>
                <form:errors path="url" class="alert-warning"/>
            </div>
            <div class="form-group">
                <form:textarea path="description" type="textarea" class="form-control"
                               placeholder="Opis..."/>
                <form:errors path="description" class="alert-warning"/>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-success text"
                       value="<spring:message code="app.save"/>">
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>