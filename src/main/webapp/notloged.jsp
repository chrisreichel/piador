<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<hr>

<c:if test="${erro != null}">
<div class="row">
    <div class="col-md-12">
        <div class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Ooopz!</strong> ${erro}
        </div>
    </div>
</div>
</c:if>

<c:if test="${sucesso != null}">
    <div class="row">
        <div class="col-md-12">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                ${sucesso}
            </div>
        </div>
    </div>
</c:if>

<div class="row">
    <div class="col-md-8">
        <div class="jumbotron">
            <h1>Welcome to Piador</h1>
            <p>Please use your credentials or create a new account.</p>
        </div>
    </div>
    <div class="col-md-4">
        <form class="form-inline" method="post" action="login">
            <div class="form-group">
                <label class="sr-only" for="usuario">Username</label>
                <input name="login" type="text" class="form-control" id="usuario" placeholder="Enter your username">
            </div>
            <div class="form-group">
                <label class="sr-only" for="senha">Your password</label>
                <input name="senha" type="password" class="form-control" id="senha" placeholder="Enter your password">
            </div>
            <button type="submit" class="btn btn-default">Login</button>
        </form>
        <hr>
        <form class="form-inline" method="post" action="usuario">
            <div class="form-group">
                <label class="sr-only" for="new.usuario">Username</label>
                <input name="login" type="text" class="form-control" id="new.usuario" placeholder="Create an username">
            </div>
            <div class="form-group">
                <label class="sr-only" for="new.email">E-mail</label>
                <input name="email" type="email" class="form-control" id="new.email" placeholder="Enter you e-mail">
            </div>
            <div class="form-group">
                <label class="sr-only" for="new.senha">Password</label>
                <input name="senha" type="password" class="form-control" id="new.senha" placeholder="Enter a password">
            </div>
            <button type="submit" class="btn btn-default">Create my account</button>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-md-8">
    </div>
    <div class="col-md-4">

    </div>
</div>

<jsp:include page="includes/footer.jsp" />