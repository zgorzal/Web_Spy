<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl-PL">
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
    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="p-5">
            <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4"><spring:message code="app.create_account"/></h1>
            </div>
            <form:form method="post" modelAttribute="user" class="user">
                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <form:input path="firstName" type="text" class="form-control form-control-user"
                                    id="exampleFirstName" placeholder="Imię"/>
                        <form:errors path="firstName" class="alert-warning"/>
                    </div>
                    <div class="col-sm-6">
                        <form:input path="lastName" type="text" class="form-control form-control-user"
                                    id="exampleLastName" placeholder="Nazwisko"/>
                        <form:errors path="lastName" class="alert-warning"/>
                    </div>
                </div>
                <div class="form-group">
                    <form:input path="email" name="email" type="email" class="form-control form-control-user"
                                id="exampleInputEmail"
                                placeholder="Email"/>
                    <form:errors path="email" class="alert-warning"/>
                </div>
                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <form:input path="password" type="password" class="form-control form-control-user"
                                    id="exampleInputPassword" placeholder="Hasło"/>
                        <form:errors path="password" class="alert-warning"/>
                    </div>
                    <div class="col-sm-6">
                        <input type="password" class="form-control form-control-user" name="repeatPassword"
                               id="exampleRepeatPassword" placeholder="<spring:message code="app.repeat_password"/>">
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-user btn-block"
                       value="<spring:message code="register.register"/>">
                <hr>
            </form:form>
            <div class="text-center">
                <a class="small" href="#"><spring:message code="app.forgot_password"/></a>
            </div>
            <div class="text-center">
                <a class="small" href="../login"><spring:message code="register.have_an_account"/></a>
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