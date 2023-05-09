package net.javaguides.login.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.javaguides.login.bean.LoginBean;
import net.javaguides.login.database.LoginDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
        	// check the login attempt is valid on loginDAO 
        	//if yes it will bring us to loginsucces.jsp page 
            if (loginDao.validate(loginBean)) {
                response.sendRedirect("loginsuccess.jsp");
            } 
         // else it will direct us to loginfail page 
            else {
            	response.sendRedirect("loginfail.jsp");
               
            }
            // use for ebugging the issue that caused the exception
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
