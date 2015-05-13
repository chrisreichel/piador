<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<jsp:include page="includes/header.jsp" />
    <H1>Hello</H1>
    <hr/>
    <form class="form-inline" method="post" action="SimpleServlet">
      <div class="form-group">
        <label class="sr-only" for="exampleInputEmail3">Email address</label>
        <input name="username" type="text" class="form-control" id="exampleInputEmail3" placeholder="Enter email">
      </div>
      <div class="form-group">
        <label class="sr-only" for="exampleInputPassword3">Password</label>
        <input name="password" type="password" class="form-control" id="exampleInputPassword3" placeholder="Password">
      </div>
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
      <button type="submit" class="btn btn-default">Sign in</button>
    </form>
<jsp:include page="includes/footer.jsp" />