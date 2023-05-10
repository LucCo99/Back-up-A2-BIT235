package net.javaguides.login.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.javaguides.login.bean.Article;
import net.javaguides.login.database.ArticleDao;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDao artDAO; // Define as instance variable

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		artDAO = new ArticleDao();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String action = request.getServletPath();
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}
		try {
			switch (action) {
			//case "new":
				//showNewEmployee(request, response);
				//break;
			//case "insert":
				//insertNewEmployee(request, response);
				//break;
			//case "delete":
				//deleteExistingEmployee(request, response);
				//break;
			//case "edit":
				//showEditEmployee(request, response);
				//break;
			//case "update":
				//updateExistingEmployee(request, response);
				//break;
			default:
				listArticle(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method
	
	private void listArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Article> allEmployee = artDAO.selectAllArticles();
		request.setAttribute("listArticle", allEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
		dispatcher.forward(request, response);
	}

}
