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
import net.javaguides.login.database.CategoriesDao;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriesDao CateDAO; // Define as instance variable

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		CateDAO = new CategoriesDao();
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
				//listCategories(request, response);
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
				listCategories(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}// End of doPost method

	
	private void listCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Categories> allEmployee = CateDAO.selectAllCategories();
		request.setAttribute("listCategories", allEmployee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Categories.jsp");
		dispatcher.forward(request, response);
	}

}
