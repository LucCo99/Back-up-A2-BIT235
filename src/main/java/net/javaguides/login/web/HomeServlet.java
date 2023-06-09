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
import net.javaguides.login.database.HomeDao;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeDao homDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() {
		homDAO = new HomeDao();
	}
	
	//
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        HomeDao HomeDao = new HomeDao();
        List<Article> articles;
        if (keyword != null && !keyword.isEmpty()) {
            articles = HomeDao.searchArticlesByKeyword(keyword);
        } else {
            articles = HomeDao.selectAllArticles();
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
			case "new":
				showArticle(request, response);
				break;
			default:
				listArticle(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method
	
	//list Individual Article
	private void showArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=show&id=
	int articleId = Integer.parseInt(request.getParameter("id"));
	Article article = homDAO.selectArticleById(articleId);
	request.setAttribute("article", article);
	RequestDispatcher dispatcher = request.getRequestDispatcher("IndividualArticle.jsp");
	dispatcher.forward(request, response);
}
	private void listArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Article> allEmployee = homDAO.selectAllArticles();
		List<Article> firstThreeArticles = allEmployee.subList(0, 3); // get the first three articles
		request.setAttribute("listArticle", firstThreeArticles);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
		dispatcher.forward(request, response);
	}

	

	
//	private void listCategories(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {//action=default
//		List<Categories> allEmployee = homDAO.selectAllCategories();
//		request.setAttribute("listCategories", allEmployee);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
//		dispatcher.forward(request, response);
//	
//
//}
}
