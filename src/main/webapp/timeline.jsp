<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Timeline</h2>

<c:if test="${self}">
<div class="row">
    <div class="col-md-2">
        <div class="pull-right">
            <h5>Novo post</h5>
        </div>
    </div>
    <div class="col-md-10">
        <form name="novoPostForm" method="post" action="novoPost">
        <div class="pull-left">
            <textarea id="novoPost" name="conteudo" maxlength="140" ></textarea>
            <button type="submit" class="btn btn-primary btn-sm">Enviar</button>
        </div>
        </form>
    </div>
</div>
</c:if>

<div class="row">
    <div class="col-md-8"><!-- pios -->
        <div id="pios">
        <c:forEach items="${pios}" var="pio">
            <div>
                <span class="label label-default"><c:out value="${pio.username}"/></span> <c:out value="${pio.conteudo}"/>
                <p class="text-muted pull-right">em: <c:out value="${pio.dataCriacao}"/></p>
            </div>
            <hr/>
        </c:forEach>
        </div>
    </div>

    <div class="col-md-4"> <!-- quem seguir -->
        <div id="quemSeguir">
            <c:forEach items="${users}" var="user">
                <div>
                    <a href="timeline?username=<c:out value="${user.authentication.userName}"/>">
                        <c:out value="${user.authentication.userName}"/>
                    </a>
                </div>
                <p>
                    <a href="seguir?user=<c:out value="${user.authentication.userName}"/>" class="btn btn-primary btn-sm">Seguir</a>
                </p>
                <hr/>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp" />