<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Wiki Application</title>
	<style>
	body {
		font-family: Arial, sans-serif;
		background-color: #f2f2f2;
	}
	
	.container {
		max-width: 800px;
		margin: 0 auto;
		padding: 20px;
		background-color: #fff;
		border-radius: 5px;
		box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
	}
	
	h1 {
		text-align: center;
		color: #333;
		margin-bottom: 20px;
	}
	
	ul {
		list-style-type: none;
		padding: 0;
		margin: 0;
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	
	li {
		margin: 0 10px;
		font-size: 18px;
		font-weight: bold;
	}
	
	li a {
		color: #333;
		text-decoration: none;
		padding: 10px;
		border-radius: 5px;
		background-color: #f2f2f2;
		transition: background-color 0.3s ease;
	}
	
	li a:hover {
		background-color: #dcdcdc;
	}
	
	table {
		border-collapse: collapse;
		width: 100%;
		margin-top: 20px;
	}
	
	th {
		background-color: #333;
		color: #fff;
		padding: 10px;
	}
	
	td {
		border: 1px solid #ddd;
		padding: 10px;
	}
</style>
</head>
<body>
	<div class="container">
		<h1>Wiki Application</h1>
		<ul>
			<li><a href="Home.jsp">Home</a></li>
			<li><a href="ArticleServlet">Articles</a></li>
			<li><a href="">Categories</a></li>
			<li><a href="login.jsp">Admin</a></li>
		</ul>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listCategories}" var="categories">
			<tr>
			<td><c:out value="${categories.getId()}" /></td>
						<td><c:out value="${categories.getTitle()}" /></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>