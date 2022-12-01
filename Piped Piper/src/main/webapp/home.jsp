<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="./home.css" />
</head>
<body>

<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	if(session.getAttribute("uname") == null){
		response.sendRedirect("index.html");
	}  

%>


<div id="top_nav_container">
    
    <div id="piper_container">
        <img src="assets/logo.png" width="10%" />
        <div id="space"></div>
        <p id="p_name">Pied Piper</p>
    </div>

    <div id="search_container">
        <input type="search" id="p_search" placeholder="Search" />
    </div>

    <div id="log_out_container">
    	<form action="Logout" method="get">
        	<input id="log_out_btn" type="submit" value="Logout" />
        </form>
        <div id="space"></div>
        <div id="space"></div>
        <div id="space"></div>
        <div id="space"></div>
    </div>

</div>

<div id="file_container">
	<form onsubmit="uploadFile()">
		<input id="ajaxfile" name="file-upload-input" type="file" required />
		<input type="submit" value="UPLOAD" id="file_upload_btn">
	</form>
</div>

<table border="1" id="info_table">

	<tr>
		<th>File Name</th>
	</tr>

<%
	String Email = (String) session.getAttribute("uname");
	String url = "jdbc:mysql://localhost:3306/pipedPiper";
	String uname = "arhant";
	String pass = "tt";

	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	try{
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection)DriverManager.getConnection(url, uname, pass);
		st = conn.createStatement();
		
		String query = "select * from UsersData where Email='" + Email + "'";
		rs = st.executeQuery(query);
		
		while(rs.next()){
			%>
			<tr>
				<td><%= rs.getString(3) %></td>
			</tr>
			<%
		}
		
	}catch (Exception e){
		e.printStackTrace();
	}

%>

</table>

<script>
async function uploadFile() {
    let formData = new FormData(); 
    formData.append("file", ajaxfile.files[0]);
    await fetch('fileuploadservlet', {
      method: "POST", 
      body: formData
    });
}

let fileInput = document.getElementById("ajaxfile");
let fileSelect = document.getElementsByClassName("file-upload-select")[0];
fileSelect.onclick = function() {
	fileInput.click();
}
fileInput.onchange = function() {
	let filename = fileInput.files[0].name;
	let selectName = document.getElementsByClassName("file-select-name")[0];
	selectName.innerText = filename;
}


</script>

</body>
</html>