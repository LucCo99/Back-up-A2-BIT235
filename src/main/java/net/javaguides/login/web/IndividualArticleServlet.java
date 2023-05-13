package net.javaguides.login.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.login.bean.Article;
import net.javaguides.login.database.ArticleDao;

@WebServlet("/IndividualArticleServlet")
public class IndividualArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArticleDao artDAO; // Define as instance variable

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndividualArticleServlet() {
        super();
    }

    @Override
    public void init() {
        artDAO = new ArticleDao();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int articleId = Integer.parseInt(request.getParameter("id"));
        Article article = artDAO.selectArticleById(articleId);
		if (article == null) {
		    request.setAttribute("error", "Sorry, no article found with the ID: " + articleId);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
		request.setAttribute("article", article);
		RequestDispatcher dispatcher = request.getRequestDispatcher("IndividualArticle.jsp");
		dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

