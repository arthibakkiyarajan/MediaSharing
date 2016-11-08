package com.mediashareing.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileLocationContextListener
 */
@WebListener
public class FileLocationContextListener implements ServletContextListener  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		        String rootPath = System.getProperty("catalina.home");
		        ServletContext ctx = servletContextEvent.getServletContext();
		        String relativePath = ctx.getInitParameter("tempfile.dir");
		        File file = new File(rootPath + File.separator + relativePath);
		        if(!file.exists()) file.mkdirs();
		        System.out.println("File Directory created to be used for storing files");
		        ctx.setAttribute("FILES_DIR_FILE", file);
		        ctx.setAttribute("FILES_DIR", rootPath + File.separator + relativePath);
		    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
