package com.mediashareing.retrievefiles;

import java.sql.*;
import java.io.*;

public class RetrieveFilesFromDB {
	public static void main(String[] args){
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://mysql -h mysql.cjlsxkfaonvz.us-west-2.rds.amazonaws.com:3306/";
		String dbName = "masterdatabase";
		String uName = "mastername";
		String pwd = "mastername";
		Connection conn = null;
		try{
		   Class.forName(driverName);
		   conn = DriverManager.getConnection(url+dbName,uName,pwd);
		   Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select file from masterdatabase.mediastore");
			int i = 0;
			while (rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				OutputStream out = new FileOutputStream(new File("test"+i+".jpg"));
				i++;
				int c = 0;
				while ((c = in.read()) > -1) {
					out.write(c);
				}
				out.close();
				in.close();
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

}
