package com.DAO;
import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 100, // 10 MB
  maxFileSize = 1024 * 1024 * 1000,      // 100 MB
  maxRequestSize = 1024 * 1024 * 10000   // 1000 MB
)
public class FileUploadServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	HttpSession s = request.getSession();
	String Email = (String) s.getAttribute("uname");
	String Name = (String) s.getAttribute("USRNAME");
    Part filePart = request.getPart("file");
    String fileName = filePart.getSubmittedFileName();
    for (Part part : request.getParts()) {
      part.write("/home/arhant/UserDataBase/" + Name +  "/" + fileName);
    }
    DAO dao = new DAO();
    String FilePath = "/home/arhant/UserDataBase/" + Name +  "/" + fileName;
    try {
		dao.add_data(Email, fileName, FilePath);
		doit(FilePath);
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
    
    response.getWriter().print("The file uploaded sucessfully.");
  }
  
  public void doit(String path) {
      try {

		Process process = Runtime.getRuntime().exec("gzip " + path);

		StringBuilder output = new StringBuilder();

		BufferedReader reader = new BufferedReader(
		new InputStreamReader(process.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}

		int exitVal = process.waitFor();
		if (exitVal == 0) {
			System.out.println("Success!");
			System.out.println(output);
			System.exit(0);
		}

	} catch (IOException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }

}
