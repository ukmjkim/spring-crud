<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>List of strategies</h1>
<p>Here you can see the list of the strategies, edit them, remove or update.</p>
<table class="table table-striped" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="10%">id</th><th width="15%">name</th><th width="10%">type</th><th width="10%">actions</th>
</tr>
</thead>
<tbody>
<c:forEach var="strategy" items="${strategies}">
<tr>
 <td>${strategy.id}</td>
 <td>${strategy.name}</td>
 <td>${strategy.type}</td>
 <td>
 <a href="${pageContext.request.contextPath}/strategy/edit/${strategy.id}.html">Edit</a>
 
 <a href="${pageContext.request.contextPath}/strategy/delete/${strategy.id}.html">Delete</a>
 
 </td>
</tr>
</c:forEach>
</tbody>
</table>
 
<p><a href="${pageContext.request.contextPath}/index.html">Home page</a></p>
 
</body>
</html>