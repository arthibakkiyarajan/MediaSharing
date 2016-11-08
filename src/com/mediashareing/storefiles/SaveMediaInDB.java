package com.mediashareing.storefiles;


import java.net.URL;
import java.sql.*;
import java.io.*;

public class SaveMediaInDB{
	public static void storeMedia(String name,String urls3, String permittedUsers){
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		try{
		   Class.forName(driverName);
		   System.out.println("Before estab conn");
		   try{
			   conn = DriverManager.getConnection(url+dbName,uName,pwd);   
		   }catch(Exception ex){
			   ex.printStackTrace();
			   System.out.println(ex.getMessage());
		   }
		   
		   System.out.println("Before estab conn");
		   Statement st = conn.createStatement();
		   /*String path="D:\\softwares\\apache-tomcat-7.0.59\\apache-tomcat-7.0.59\\tmpfiles\\"+filename;
		   File file = new File(path);
		   FileInputStream fin = new FileInputStream(file);*/
		   //System.out.println("file::::::::::"+file.getName());
		   System.out.println("name:"+name);
		  // System.out.println("urls:3:"+urls3);
		   System.out.println("permittedUsers:"+permittedUsers);
		   
		   PreparedStatement ps =
		   conn.prepareStatement("insert into masterdatabase.mediastore values(?,?,?)");
		   ps.setString(1,name);
		   ps.setString(2,urls3);
		  // ps.setBinaryStream(2,(InputStream)fin,(int)file.length());
		   System.out.println("1permittedUsers"+permittedUsers);
		   ps.setString(3, permittedUsers);
		  // ps.setString(4,"pet");
		   //ps.setString(5,"mom");
		   ps.executeUpdate();
		   ps.close();
		   conn.close(); 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}