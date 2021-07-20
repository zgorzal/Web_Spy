<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">${page.name}</h6>
    </div>
    <div class="card-body">
        <iframe src="https://www.filmweb.pl" width="100%" height="400"></iframe>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>