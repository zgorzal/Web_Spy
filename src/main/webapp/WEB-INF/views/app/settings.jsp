<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="row">
    <div class="col-lg-5 mb-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><spring:message code="settings.change_data"/></h6>
            </div>
            <div class="card-body">
                <form:form method="post" modelAttribute="user">
                    <div class="row-cols-1">
                        <label>
                            <form:input path="firstName" type="text" class="form-control"
                                        placeholder="Imię"/>
                            <form:errors path="firstName" class="alert-warning"/>
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <form:input path="lastName" type="text" class="form-control"
                                        placeholder="Nazwisko"/>
                            <form:errors path="lastName" class="alert-warning"/>
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <form:input path="email" type="email" class="form-control"
                                        placeholder="Email"/>
                            <form:errors path="email" class="alert-warning"/>
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <input type="submit" class="btn btn-success text"
                               value="<spring:message code="settings.change_approves"/>">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-lg-5 mb-4">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary"><spring:message
                        code="settings.change_password"/></h6>
            </div>
            <div class="card-body">
                <form:form method="post" modelAttribute="user" action="/user/settings/password">
                    <div class="row-cols-1">
                        <label>
                            <input type="password" name="oldPassword" class="form-control"
                                   placeholder="Stare hasło">
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <input type="password" name="newPassword" class="form-control"
                                   placeholder="Nowe hasło">
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <input type="password" name="repeatNewPassword" class="form-control"
                                   placeholder="Powtórz nowe hasło">
                        </label>
                    </div>
                    <div class="row-cols-1">
                        <input type="submit" class="btn btn-warning text"
                               value="<spring:message code="settings.change_password_approves"/>">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>