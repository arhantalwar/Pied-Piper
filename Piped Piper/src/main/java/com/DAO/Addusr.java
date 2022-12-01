package com.DAO;

import java.io.IOException;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addusr")
public class Addusr extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String FirstName = req.getParameter("firstname");
		String LastName = req.getParameter("lastname");
		String Email = req.getParameter("email");
		String PSWD = req.getParameter("password");
		
		DAO dao = new DAO();
		Create_folder cf = new Create_folder();
		
		try {
			dao.add_usr(FirstName, LastName, Email, PSWD);
			cf.mk_folder(FirstName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		res.sendRedirect("home.jsp");
		
	}
	
}
