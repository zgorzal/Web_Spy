<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary"><spring:message code="dashboard.page_list"/></h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid"
                   aria-describedby="dataTable_info" style="width: 100%;">

                <thead>
                <tr role="row">
                    <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-sort="ascending" aria-label="Name: activate to sort column descending"
                        style="width: 157px;"><spring:message code="dashboard.action"/>
                    </th>
                    <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-sort="ascending" aria-label="Name: activate to sort column descending"
                        style="width: 157px;"><spring:message code="page_id"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Position: activate to sort column ascending" style="width: 259px;"><spring:message
                            code="page_name"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Office: activate to sort column ascending" style="width: 117px;"><spring:message
                            code="page_URL"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Age: activate to sort column ascending" style="width: 50px;"><spring:message
                            code="page_description"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Start date: activate to sort column ascending" style="width: 104px;"><spring:message
                            code="page_data_add"/>
                    </th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1"
                        aria-label="Salary: activate to sort column ascending" style="width: 91px;"><spring:message
                            code="page_data_update"/>
                    </th>
                </tr>
                </thead>
                <c:forEach items="${pages}" var="page">
                    <tr class="odd">
                        <td>
                            <a href="/page/edit/${page.id}"><spring:message code="app.edit"/></a><br>
                            <a href="/page/delete/${page.id}"><spring:message code="app.delete"/></a>
                        </td>
                        <td>${page.id}</td>
                        <td>${page.name}</td>
                        <td>${page.url}</td>
                        <td>${page.description}</td>
                        <td>${page.dateAdded}</td>
                        <td>${page.dateUpdate}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>