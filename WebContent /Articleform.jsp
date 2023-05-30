<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Employees</title>
</head>
<body>
	<center>
		<h4>
			<a href="${pageContext.request.contextPath}/AdminServlet">List All Employees</a>
		</h4>
	</center>
	<div align="center">
		<c:if test="${article != null}">
			<form action="${pageContext.request.contextPath}/AdminServlet" method="post">
				<input type="hidden" name="action" value="update">
		</c:if>
		<c:if test="${article == null}">
			<form action="${pageContext.request.contextPath}/AdminServlet?action=insert" method="post">
		</c:if>
				<table border="1" cellpadding="5">
					<caption>
						<h2>
							<c:if test="${article != null}"> Edit Article </c:if>
							<c:if test="${article == null}"> Add New Article </c:if>
						</h2>
					</caption>
					<c:if test="${article != null}">
						<input type="hidden" name="id" value="<c:out value='${article.getId()}' />" />
					</c:if>
					<tr>
						<th>Input Title:</th>
						<td><input type="text" name="title" size="45" required value="<c:out value='${article.getTitle()}' />" /></td>
					</tr>
					<tr>
						<th>Content:</th>
						<td><input type="text" name="body" size="90" required value="<c:out value='${article.getBody()}' />" /></td>
					</tr>
					<tr>
  						<th>Date:</th>
  						<td><input type="date" name="date" min="2021-01-01" required value="<c:out value='${article.getDate()}' />" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="Save" /></td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>