package com.mediashareing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayFilesServlet
 */
@WebServlet("/DisplayFilesServlet")
public class DisplayFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		try{
			System.out.println("starting.............");
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select filename,url from masterdatabase.mediastore");
			int i = 0;
			System.out.println("ending.............");
			int user=(int) request.getSession().getAttribute("user");
			System.out.println("user"+user);
			
			response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.write("<html><head></head><body bgcolor=#F5ECCE ><font size='4'><p align='right'><a href='/MediaSharing/index.jsp'>Home</a></p><br/><font size=6 color='red'>Click on view link to view image</font><br/><br/>");
			while (rs.next()) {
				int flag=0;
					if(!rs.getString("filename").equals(null) && (rs.getString("filename").length()>0)){
				/*		String permittedusers=rs.getString("permittedusers");
						if(!permittedusers.equals(null) && (permittedusers.length()>0)){
								System.out.println("permittedusers"+permittedusers);
								String users[] = permittedusers.split(";");
								System.out.println("users.length"+users.length);
								for(int j=0;j<users.length;j++){
									flag=0;
									System.out.println(j+" "+users[j]);
									if(Integer.valueOf(users[j]).equals(user)){
										flag=1;
										System.out.println("inside if");
									}
									//System.out.println(rs.getObject(1).toString());
									if(flag==1){ */
									System.out.println("rs.getString('url')"+rs.getString("url"));
							        out.write("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getObject(1).toString());
					                out.write("&nbsp;&nbsp;<a href=\""+rs.getString("url")+"\">View </a><br/>");
					                out.write("<br>");
							/*	}
							}
						}*/
					}
		        }
		        out.write("</font></body></html>");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
