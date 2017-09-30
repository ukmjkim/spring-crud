<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Edit strategy page</h1>
<p>Here you can edit the existing strategy.</p>
<p>${message}</p>
<form:form method="POST" commandName="strategy" action="${pageContext.request.contextPath}/strategy/edit/${strategy.id}.html">
<table>
<tbody>
 <tr>
  <td>Name:</td>
  <td><form:input path="name" /></td>
 </tr>
 <tr>
  <td>Type:</td>
  <td><form:input path="type" /></td>
 </tr>
 <tr>
  <td><input type="submit" value="Edit" /></td>
  <td></td>
 </tr>
</tbody>
</table>
</form:form>
 
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>

</body>
</html>