<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>

<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Potwierdź akcję</h6>
    </div>
    <div class="card-body">
        <form:form method="post" modelAttribute="page">
            <div class="form-group">
                <h6>Czy na pewno chcesz usunać stronę : ${page.name}</h6>
                <input type="submit" class="btn btn-danger text"
                       value="Usuń">
                <a href="/page/list">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                </a>
            </div>
        </form:form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>