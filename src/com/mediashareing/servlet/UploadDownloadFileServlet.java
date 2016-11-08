package com.mediashareing.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import s3service.S3Service;

import com.mediashareing.storefiles.SaveMediaInDB;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;

	@Override
	public void init() throws ServletException {
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute(
				"FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		if (fileName == null || fileName.equals("")) {
			throw new ServletException("File Name can't be null or empty");
		}
		File file = new File(request.getServletContext().getAttribute(
				"FILES_DIR")
				+ File.separator + fileName);
		if (!file.exists()) {
			throw new ServletException("File doesn't exists on server.");
		}
		System.out
				.println("File location on server::" + file.getAbsolutePath());
		ServletContext ctx = getServletContext();
		InputStream fis = new FileInputStream(file);
		String mimeType = ctx.getMimeType(file.getAbsolutePath());
		response.setContentType(mimeType != null ? mimeType
				: "application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ fileName + "\"");
		ServletOutputStream os = response.getOutputStream();
		byte[] bufferData = new byte[1024];
		int read = 0;
		while ((read = fis.read(bufferData)) != -1) {
			os.write(bufferData, 0, read);
		}
		os.flush();
		os.close();
		fis.close();
		System.out.println("File downloaded at client successfully");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException(
					"Content type is not multipart/form-data");
		}

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		String permission = "";
		PrintWriter out = response.getWriter();
		
		try {
			String filename = request.getParameter("filename");
			filename = (String) request.getAttribute("filename");
			response.setContentType("text/html");
			
			out.write("<html><head></head><body bgcolor=#F5ECCE align=center><br/><br/><br/><br/><br/><p align='right'><a href='/MediaSharing/upload.jsp'><font size='4'>Home</a></p><br/><br/><br/><br/><br/><font size=6 color='red'>");
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			File file=new File("temp.jpg");
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				if (fileItem.getFieldName().equals("file")) {
					
					InputStream inputStream = fileItem.getInputStream();
					OutputStream outputStream = null;
					outputStream = 
		                    new FileOutputStream(file);
		 
				int read = 0;
				byte[] bytes = new byte[1024];
		 
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
		 
					filename = fileItem.getName();
					System.out.println("File Name Received:"+filename);
					out.write("File " + fileItem.getName()
							+ " has been uploaded successfully.");
					out.write("<br>");
					fileItem.write(file);
				}
				
				
				/*File file = new File(request.getServletContext().getAttribute(
						"FILES_DIR")
						+ File.separator + fileItem.getName());*/
				
				
				/*if (fileItem.getFieldName().equals("emaillist")) {
					String emaillist = fileItem.getString();
					Connection conn = null;
					try {
						Class.forName(driverName);
						conn = DriverManager.getConnection(url + dbName, uName,
								pwd);
						Statement st = conn.createStatement();
						ResultSet rs = st
								.executeQuery("select userid,emailid from masterdatabase.userinfo");
						List tempList = new ArrayList();
						while (rs.next()) {
							tempList.add(rs.getString("emailid"));
							if (permission == "")
								permission += rs.getString("userid");
							else
								permission += ";" + rs.getString("userid");
						}
						String[] li = emaillist.split(";");
						String err = "";
						for (int i = 0; i < li.length; i++) {
							if (!tempList.contains(li[i])) {
								err += li[i];
							}
						}
						// response.setAttribute("err", err);
						if (!err.isEmpty()) {
							response.sendRedirect("upload.jsp?err=" + err);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}*/
				
				try {
					
					ServletContext context = getServletContext();
					String keypath = context.getRealPath("/WEB-INF/new.der");
					
				

					if (fileItem.getName() != null) {
						String urlS3 = "here";
						//call s3 and upload image and get URL store it in urlS3
						String s3url = S3Service.uploadFileToS3(filename, file,keypath);
						System.out.println("before calling storeMedia");
						SaveMediaInDB.storeMedia(fileItem.getName(), s3url,permission);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			out.write("Exception in uploading file.");
			e.printStackTrace();
		}
		out.write("</font></body></html>");
	}
}
