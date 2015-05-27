<%@ page isErrorPage="true" import="java.io.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Um erro ocorreu no piador: <%=exception.getMessage()%></title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-theme.min.css">
  <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>500 - Erro interno</h1>
<hr>
<div class="panel panel-danger">
  <div class="panel-heading">StackTrace</div>
  <div class="panel-body">
    <pre>
    <%
    	StringWriter stringWriter = new StringWriter();
    	PrintWriter printWriter = new PrintWriter(stringWriter);
    	exception.printStackTrace(printWriter);
    	out.println(stringWriter);
    	printWriter.close();
    	stringWriter.close();
    %>
    </pre>
  </div>
</div>
<hr>
</div>
</body>
</html>