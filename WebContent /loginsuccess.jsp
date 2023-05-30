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
	
	.article-title {
  font-weight: bold;
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
	<form method="get" action="ArticleServlet" style="position: absolute; top: 20px; right: 20px;">
  <input type="text" name="keyword" placeholder="Search articles...">
  <button type="submit">Search</button>
</form>
    <h1>Wiki Application</h1>
    <ul>
        <li><a href="Articleform.jsp">Create New Article</a></li>
        <li><a href="Categoryform.jsp">Create New Category</a></li>
        <li><a href="AdminServlet?action=hidelist">Hidden Article List</a></li>
        <li><a href="Home.jsp">Logout</a></li>
    </ul>
    <table>
        <tbody>
        	<h2>All Article</h2>
        	  <c:forEach var="article" items="${listArticle}">
                <tr>
                    <td>
                        <div class="article-title"><c:out value="${article.getTitle()}" />
                        <a href="${pageContext.request.contextPath}/AdminServlet?action=edit&id=<c:out value='${article.getId()}' />">Edit</a>|
                        <a onclick="myFunction()" href="${pageContext.request.contextPath}/AdminServlet?action=delete&id=<c:out value='${article.getId()}' />">Delete</a>|
                        <a href="${pageContext.request.contextPath}/AdminServlet?action=hide&id=<c:out value='${article.getId()}' />">Hide</a>
                        </div>
                        
                        <div class="article-body"><c:out value="${article.getBody()}" /></div>
                       
                       <td>|<a href="${pageContext.request.contextPath}/IndividualArticleServlet?action=view&id=<c:out value='${article.getId()}' />">View</a>|</td>
      
                       <script>
function myFunction() {
	 let confirmationMessage = "Are you sure you want to delete this article?";
	  if (confirm(confirmationMessage)) {
	    // User clicked "OK", proceed with the deletion
	    window.location.href = `${pageContext.request.contextPath}/AdminServlet?action=delete&id=${article.getId()}`;
	  } else {
	    // User clicked "Cancel", do nothing
	    console.log("Deletion canceled by the user.");
	  }
}
</script>
                    </td>
                </tr>
            </c:forEach>
           
        </tbody>
    </table>
</div>
</body>
</html>