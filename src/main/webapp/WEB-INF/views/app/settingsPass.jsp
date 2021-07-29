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
                <h6 class="m-0 font-weight-bold text-primary"><spring:message
                        code="settings.change_password"/></h6>
            </div>
            <div class="card-body">
                <form:form method="post" modelAttribute="userPasswordChangeDTO">
                    <form:hidden path="id"/>
                    <div class="row-cols-1">
                        <label>
                            <form:input path="oldPassword" type="password" class="form-control"
                                        placeholder="Stare hasło"/>
                        </label>
                        <form:errors path="oldPassword" class="alert-warning"/>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <form:input path="newPassword" type="password" class="form-control"
                                        placeholder="Nowe hasło"/>
                        </label>
                        <form:errors path="newPassword" class="alert-warning"/>
                    </div>
                    <div class="row-cols-1">
                        <label>
                            <form:input path="repeatPassword" type="password" class="form-control"
                                        placeholder="Powtórz nowe hasło"/>
                        </label>
                        <form:errors path="repeatPassword" class="alert-warning"/>
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