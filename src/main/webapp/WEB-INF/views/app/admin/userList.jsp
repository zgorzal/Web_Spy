<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="dashboard.users_list"/></h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid"
                   aria-describedby="dataTable_info" style="width: 100%;">

                <thead>
                <tr role="row">
                    <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-sort="ascending" aria-label="Name: activate to sort column descending"
                        style="width: 157px;"><spring:message code="user_id"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Position: activate to sort column ascending" style="width: 259px;"><spring:message
                            code="user_first_name"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Office: activate to sort column ascending" style="width: 117px;"><spring:message
                            code="user_last_name"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Age: activate to sort column ascending" style="width: 50px;"><spring:message
                            code="user_email"/>
                    </th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr class="odd">
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>