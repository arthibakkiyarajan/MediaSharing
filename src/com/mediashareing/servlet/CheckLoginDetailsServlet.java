package com.mediashareing.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mediashareing.vo.LoginPersonVO;
import com.sun.corba.se.spi.activation.Server;

/**
 * Servlet implementation class CheckLoginDetailsServlet
 */
@WebServlet("/CheckLoginDetailsServlet")
public class CheckLoginDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List volist = new ArrayList();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginDetailsServlet() {
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
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   
		   if(conn == null){
			   System.out.println("Connection object is null");
		   } else {
			   if(conn.isClosed()){
				   System.out.println("Connection Is Closed");
			   }
		   }
		   Statement st = conn.createStatement();
		   ResultSet rs = st.executeQuery("select userid,emailid,password from masterdatabase.userinfo");
		   while (rs.next()) {
			   LoginPersonVO vo= new LoginPersonVO();
			   vo.setId(rs.getInt("userid"));
			   vo.setEmail(rs.getString("emailid"));
			   vo.setPwd(rs.getString("password"));
//			   System.out.println("userid"+rs.getInt("userid"));
			   volist.add(vo);
		   }
		   conn.close();
		   response.sendRedirect("index.jsp");
		}catch (Exception e){
			System.out.println("Exception"+e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		populateVoList();
		/*byte[] derPrivateKey;
		
		System.out.println("Application Context Path:"+request.getContextPath());
		
		try{
			String fullPath = request.getContextPath();
			Path path = Paths.get(fullPath+"/new.der");
			derPrivateKey = Files.readAllBytes(path);
			System.out.println("Success 1");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			
			ServletContext context = getServletContext();
			String fullPath = context.getRealPath("/WEB-INF/new.der");
			Path path = Paths.get(fullPath);
			derPrivateKey = Files.readAllBytes(path);
			System.out.println("Success 2");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			
			ServletContext context = getServletContext();
			String fullPath = context.getRealPath("/WEB-INF/classes/new.der");
			Path path = Paths.get(fullPath);
			derPrivateKey = Files.readAllBytes(path);
			System.out.println("Success 3");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			
			
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("new.der").getFile());
			
			Path path = Paths.get(file.getAbsolutePath());
			derPrivateKey = Files.readAllBytes(path); 
			System.out.println("Success 4");
		}catch(Exception e){
			System.out.println("Key File Reading Exception:"+e.getMessage());
			e.printStackTrace();
		}*/
		
		
		
		String emailid = request.getParameter("email");
		String pwd = request.getParameter("password");
		int flag=0;
//		System.out.println("size"+volist.size());
		for(int i=0;i<volist.size();i++){
			LoginPersonVO vo=(LoginPersonVO)volist.get(i);
//			System.out.println("vo.getEmail()"+vo.getEmail());
//			System.out.println("emailid"+emailid);
			if(vo.getEmail().equals(emailid)&&vo.getPwd().equals(pwd)){
				flag=vo.getId();
			}
		}
		if(flag!=0){
			if(request.getSession().getAttribute("user")!=null){
				request.getSession().removeAttribute("user");	
			}
			request.getSession().setAttribute("user", flag);
			response.sendRedirect("upload.jsp");
			request.setAttribute("err", 0);
		}
		else{
			request.setAttribute("err", 1);
			//response.sendRedirect("index.jsp?err=1");
			request.getRequestDispatcher("index.jsp").forward(request, response); 
		}
	}
	public void populateVoList(){
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		try{
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   
		   if(conn == null){
			   System.out.println("Connection object is null");
		   } else {
			   if(conn.isClosed()){
				   System.out.println("Connection Is Closed");
			   }
		   }
		   Statement st = conn.createStatement();
		   ResultSet rs = st.executeQuery("select userid,emailid,password from masterdatabase.userinfo");
		   while (rs.next()) {
			   LoginPersonVO vo= new LoginPersonVO();
			   vo.setId(rs.getInt("userid"));
			   vo.setEmail(rs.getString("emailid"));
			   vo.setPwd(rs.getString("password"));
			   System.out.println("userid"+rs.getInt("userid"));
			   volist.add(vo);
		   }
		   conn.close();
		}catch (Exception e){
			System.out.println("Exception"+e.getMessage());
			e.printStackTrace();
		}
	}
}
