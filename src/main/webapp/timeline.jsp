<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<jsp:include page="includes/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>Timeline</h2>

<c:if test="${self != null}">
<div class="row">
    <div class="col-md-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                New post
            </div>
            <div class="panel-body">
                <form name="novoPostForm" method="post" action="novoPost">
                    <div class="form-group">
                        <textarea class="form-control" id="novoPost" name="conteudo" maxlength="140" ></textarea>
                        <button type="submit" class="btn btn-primary btn-sm">Send</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
    <div class="col-md-4">
        <h4>My profile</h4>
        <div>
            <img src="http://www.linkirado.net/img_post/a0b1045b09f03dac4c1f86bd9e3b75ff.jpg" alt="Soy djo" class="img-circle">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <hr/>
    </div>
</div>
</c:if>

<div class="row">
    <div class="col-md-8"><!-- pios -->
        <div id="pios">
        <c:forEach items="${pios}" var="pio">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <c:choose>
                        <c:when test="${self == pio.username}">
                            <span class="label label-info"><c:out value="${pio.username}"/></span>
                        </c:when>
                        <c:otherwise>
                            <span class="label label-default"><c:out value="${pio.username}"/></span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="panel-body">
                    <c:out value="${pio.conteudo}"/>
                </div>
                <div class="panel-footer">
                    <fmt:parseDate value="${pio.dataCriacao}" pattern="yyyy-MM-dd"
                                   var="parsedDate" type="date" />
                    <div class="text-muted"><fmt:formatDate type="both"
                                                                dateStyle="long" timeStyle="short" value="${parsedDate}" /></div>
                    <c:if test="${self == pio.username}">
                    <div class="pull-right">
                        <button type="button" class="btn btn-danger btn-xs" onclick="alert('Nao funciona')">Remover</button>
                    </div>
                        <div class="clearfix"></div>
                    </c:if>
                </div>
            </div>
            <hr/>
        </c:forEach>
        </div>
    </div>

    <div class="col-md-4"> <!-- quem seguir -->
        <div id="quemSeguir">
            <c:forEach items="${users}" var="user">
                <c:if test="${self != user.authentication.userName}">
                <div>
                    <a href="timeline?username=<c:out value="${user.authentication.userName}"/>">
                        <c:out value="${user.authentication.userName}"/>
                    </a>
                </div>
                <p>
                    <a href="seguir?user=<c:out value="${user.authentication.userName}"/>" class="btn btn-primary btn-sm">Follow</a>
                </p>
                <hr/>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp" />