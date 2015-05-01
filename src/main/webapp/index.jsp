<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Simple Servlet</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-theme.min.css">
  <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
<h1>Simple Servlet</h1>
Abrir <a href="${pageContext.request.contextPath}/SimpleServlet"/>SimpleServlet</a>.
<hr>
Nosso Dojo <a href="${pageContext.request.contextPath}/timeline?username=@marcio"/>TimeLineServlet</a>.
<hr>
Abrir <a href="${pageContext.request.contextPath}/LoadPageServlet"/>LoadPageServlet</a>.
</div>
</body>
</html>