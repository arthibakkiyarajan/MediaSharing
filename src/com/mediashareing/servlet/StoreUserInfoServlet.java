package com.mediashareing.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreUserInfoServlet
 */
@WebServlet("/StoreUserInfoServlet")
public class StoreUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StoreUserInfoServlet doGet request received");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String password=request.getParameter("pwd");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String pet=request.getParameter("pet");
		String mothername=request.getParameter("mothername");
		try{
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   Statement st = conn.createStatement();
		   PreparedStatement ps =
		   conn.prepareStatement("insert into masterdatabase.userinfo (firstname,lastname,emailid,password,telephoneno,pet,mothername) values('"+fname+"','"+lname+"','"+email+"','"+password+"','"+phone+"','"+pet+"','"+mothername+"')");
		   ps.executeUpdate();
		   ps.close();
		   conn.close();
		   response.sendRedirect("upload.jsp");
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" 1 StoreUserInfoServlet doPost request received");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String password=request.getParameter("pwd");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String pet=request.getParameter("pet");
		String mothername=request.getParameter("mothername");
		try{
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   Statement st = conn.createStatement();
		   PreparedStatement ps =
		   conn.prepareStatement("insert into masterdatabase.userinfo (firstname,lastname,emailid,password,telephoneno,pet,mothername) values('"+fname+"','"+lname+"','"+email+"','"+password+"','"+phone+"','"+pet+"','"+mothername+"')");
		   ps.executeUpdate();
		   ps.close();
		   conn.close();
		   response.sendRedirect("index.jsp");
		}catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
