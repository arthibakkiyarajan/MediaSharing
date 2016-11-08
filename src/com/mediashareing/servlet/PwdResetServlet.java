package com.mediashareing.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PwdResetServlet
 */
@WebServlet("/PwdResetServlet")
public class PwdResetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		try{
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   Statement st = conn.createStatement();
		   String uname= request.getParameter("username");
		   String pass=request.getParameter("pwd");
		   System.out.println("uname"+uname);
		   System.out.println("pass"+pass);
		   if(pass!=null && pass.length()>0){
			   PreparedStatement ps = conn.prepareStatement("update masterdatabase.userinfo set password = '"+pass+"' where emailid='"+uname+"'");
			   ps.executeUpdate();
			   ps.close();
			   response.sendRedirect("index.jsp");
		   }
		   else{
			   
			   System.out.println("uname"+uname);
				ResultSet rs = st.executeQuery("select pet,mothername from masterdatabase.userinfo where emailid='"+uname+"'");
				System.out.println("rs"+rs);
				request.setAttribute("username", uname);
				//System.out.println(rs.getString("pet"));
				if(rs.next()){
					 String pet=rs.getString("pet");
					 String mname=rs.getString("mothername");
					request.setAttribute("pet",pet);
					request.setAttribute("mothername", mname);	
				}
				else{
					request.setAttribute("invaliduser", true);
				}
				request.getRequestDispatcher("forgotPwd.jsp").forward(request, response);   
		   }
		   
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
