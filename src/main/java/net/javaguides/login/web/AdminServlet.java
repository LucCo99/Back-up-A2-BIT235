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

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDao artDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init() {
		artDAO = new ArticleDao();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			case "view":
				showArticle(request, response);
				break;
			case "insert":
				insertNewArticle(request, response);
				break;
			case "insertC":
				insertNewCategory(request, response);
				break;
			case "delete":
				deleteExistingArticle(request, response);
				break;
			case "edit":
				showEditArticle(request, response);
				break;
			case "hide":
				hideExistingArticle(request, response);
				break;
				case "hidelist":
				listHideArticle(request, response);
				break;
			case "update":
				updateArticle(request, response);
				break;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginsuccess.jsp");
		dispatcher.forward(request, response);
	}
	
	//list of hide article
	private void listHideArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Article> allEmployee = artDAO.selectHideArticles();
		request.setAttribute("listArticle", allEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginsuccess.jsp");
		dispatcher.forward(request, response);
	}
	
	//list Individual Article
	private void showArticle(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException, ServletException {
	    int articleId = Integer.parseInt(request.getParameter("id"));
	    Article article = artDAO.selectArticleById(articleId);
	    request.setAttribute("article", article);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("IndividualArticle.jsp");
	    dispatcher.forward(request, response);
	}
	
	private void showEditArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {//action="edit"
		int id = Integer.parseInt(request.getParameter("id"));
		Article existingArticle = artDAO.selectArticle(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Articleform.jsp");
		request.setAttribute("article", existingArticle);
		dispatcher.forward(request, response);
	} 
	
	private void updateArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="update"
		int id = Integer.parseInt(request.getParameter("id"));
		String etitle = request.getParameter("title");
		String ebody = request.getParameter("body");
		String edate = request.getParameter("date");
		String status = request.getParameter("status");
		Article e = new Article(id, etitle, ebody, edate);
		artDAO.updateArticle(e);
		response.sendRedirect(request.getContextPath() + "/AdminServlet?action=list");
	}
	
	private void deleteExistingArticle(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		artDAO.deleteArticle(id);
	    response.sendRedirect(request.getContextPath() +"/AdminServlet?action=list");
	}
	
	private void insertNewArticle(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="insert"
		String eTitle = request.getParameter("title");
		String eBody = request.getParameter("body");
		String eDateStr = request.getParameter("date");
		Article e = new Article(eTitle, eBody, eDateStr);
		artDAO.insertArticle(e);
		response.sendRedirect(request.getContextPath() + "/AdminServlet?action=____");
	}
	private void insertNewCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="insert"
		String eCategory = request.getParameter("category");
		Article e = new Article(eCategory);
		artDAO.insertCategory(e);
		response.sendRedirect(request.getContextPath() + "/CategoriesServlet");
	}
	
	private void hideExistingArticle(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    
	    // Update the article status to hidden in the database
	    artDAO.setArticlePrivate(id);
	    response.sendRedirect(request.getContextPath() + "/AdminServlet?action=list");
	}




//	private void listArticle(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {
//		List<Article> allEmployee = artDAO.selectAllArticles();
//		List<Article> firstThreeArticles = allEmployee.subList(0, 3); // get the first three articles
//		request.setAttribute("listArticle", firstThreeArticles);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Articles.jsp");
//		dispatcher.forward(request, response);
//	}

	

	
//	private void listCategories(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException, ServletException {//action=default
//		List<Categories> allEmployee = artDAO.selectAllCategories();
//		request.setAttribute("listCategories", allEmployee);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Categories.jsp");
//		dispatcher.forward(request, response);
//	}


}
