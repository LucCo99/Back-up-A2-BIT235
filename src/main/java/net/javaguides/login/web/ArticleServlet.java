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

import net.javaguides.login.bean.Categories;
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
	
	//
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        ArticleDao articleDao = new ArticleDao();
        List<Article> articles;
        if (keyword != null && !keyword.isEmpty()) {
            articles = articleDao.searchArticlesByKeyword(keyword);
        } else {
            articles = articleDao.selectAllArticles();
        }
        request.setAttribute("listArticle", articles);
        request.getRequestDispatcher("Articles.jsp").forward(request, response);
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
			case "view":
				showArticle(request, response);
				break;
			//case "insert":
				//insertNewEmployee(request, response);
				//break;
			//case "delete":
				//deleteExistingEmployee(request, response);
				//break;
			//case "edit":
				//showEditEmployee(request, response);
				//break;
//			case "list all article":
//				listArticles(request, response);
//				break;
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
	
	//list Individual Article
	private void showArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=show&id=
	int articleId = Integer.parseInt(request.getParameter("id"));
	Article article = artDAO.selectArticleById(articleId);
	request.setAttribute("article", article);
	RequestDispatcher dispatcher = request.getRequestDispatcher("Article.jsp");
	dispatcher.forward(request, response);
}
//	private void listArticle(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		List<Article> allEmployee = artDAO.selectAllArticles();
//		List<Article> firstThreeArticles = allEmployee.subList(0, 3); // get the first three articles
//		request.setAttribute("listArticle", firstThreeArticles);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
//		dispatcher.forward(request, response);
//	}

	

	
	private void listCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Categories> allEmployee = artDAO.selectAllCategories();
		request.setAttribute("listCategories", allEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
		dispatcher.forward(request, response);
	}


}
