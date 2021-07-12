<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="app.title"/></title>

    <link href="../../theme/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="../../theme/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="bg-gradient-primary">
<div class="container">
    <div class="row justify-content-center">
        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="p-5">
                <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4"><spring:message code="login.welcome_back"/></h1>
                </div>
                <form class="user" method="post">
                    <div class="form-group">
                        <input type="email" class="form-control form-control-user"
                               id="exampleInputEmail" aria-describedby="emailHelp"
                               placeholder="<spring:message code="login.enter_email"/>">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control form-control-user"
                               id="exampleInputPassword" placeholder="<spring:message code="login.enter_password"/>">
                    </div>
                    <a href="#" class="btn btn-primary btn-user btn-block">
                        <spring:message code="login.login"/>
                    </a>
                    <hr>
                </form>
                <div class="text-center">
                    <a class="small" href="#"><spring:message code="login.forgot_password"/></a>
                </div>
                <div class="text-center">
                    <a class="small" href="#"><spring:message code="login.create_account"/></a>
                </div>
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