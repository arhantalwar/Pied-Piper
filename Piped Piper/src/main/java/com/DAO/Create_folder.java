package com.DAO;

import java.io.File;

public class Create_folder {

	 public void mk_folder(String Uname) {
	     String path = "/home/arhant/UserDataBase/";
	     path = path+Uname;
	     File f1 = new File(path);  
	     boolean bool = f1.mkdir();
	     if(bool){  
	        System.out.println("Folder is created successfully");  
	     }else{  
	        System.out.println("Error Found!");  
	     }
	 }
}