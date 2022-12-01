package com.DAO;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginV")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Email = req.getParameter("lemail");
		String PSWD = req.getParameter("lpassword");
		String USRNAME = null;
		boolean verify = false;
		
		DAO dao = new DAO();
		
		try {
			verify = dao.check_usr(Email, PSWD);
			USRNAME = dao.get_name(Email);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(verify) {
			HttpSession session = req.getSession();
			session.setAttribute("uname", Email);
			session.setAttribute("USRNAME", USRNAME);
			resp.sendRedirect("home.jsp");
		}else {
			resp.sendRedirect("index.html");
		}
		
		
	}
	
}
