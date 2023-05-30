<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f2f2f2;
			margin: 0;
			padding: 0;
		}
	
		.container {
			max-width: 800px;
			margin: 20px auto;
			padding: 20px;
			background-color: #fff;
			border-radius: 5px;
			box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
		}
	
		.article-title {
			font-weight: bold;
			font-size: 36px;
			color: #333;
			margin: 0 0 10px 0;
		}
	
		h1 {
			text-align: center;
			margin: 30px 0;
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
	<meta charset="UTF-8">
	<title><c:out value="${article.getTitle()}" /></title>
</head>
<body>
	<div class="container">
		<h1>Article Details</h1>
		<h2 class="article-title"><c:out value="${article.getTitle()}" /></h2>
		<p>Date: <c:out value="${article.getDate()}" /></p>
		<p>Category: <c:out value="${article.getCategory()}" /></p> 
		<hr>
		<p><c:out value="${article.getBody()}" /></p>
	</div>
</body>
</html>


