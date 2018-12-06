package org.project.testengine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface CommonDAO {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con=null;
		
		ResourceBundle rb=ResourceBundle.getBundle("config");//read congig.properties line 
		Class.forName(rb.getString("driver"));

		 con=DriverManager.getConnection(rb.getString("url"),//DriverManger j
				rb.getString("userid"),rb.getString("password"));
		 return con;
		 
	}
}
