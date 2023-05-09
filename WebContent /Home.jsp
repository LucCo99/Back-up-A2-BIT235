<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	</style>
</head>
<body>
	<div class="container">
		<h1>Wiki Application</h1>
		<ul>
				<li><a href="#">Home</a></li>
				<li><a href="Articles.jsp">Articles</a></li>
				<li><a href="#">Categories</a></li>
				<li><a href="login.jsp">Admin</a></li>
		</ul>
	</div>
</body>
</html>
