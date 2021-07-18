
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span> <spring:message code="app.footer_text"/></span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><spring:message code="logout.sure"/></h5>
            </div>
            <div class="modal-body"><spring:message code="logout.message"/></div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal"><spring:message
                        code="app.cancel"/></button>
                <form action="<c:url value="/logout"/>" method="post">
                    <input class="btn btn-primary" type="submit" value="<spring:message code="dashboard.logout"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../../theme/vendor/jquery/jquery.min.js"></script>
<script src="../../theme/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="../../theme/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="../../theme/js/sb-admin-2.min.js"></script>
</body>
</html>