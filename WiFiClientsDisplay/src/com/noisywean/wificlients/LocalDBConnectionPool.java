package com.noisywean.wificlients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDBConnectionPool {
	
	private static Connection connection;	
	//DESKTOP-BI5PID5\\SQLEXPRESS
	public static Connection getConnection()
 {
		if (connection == null) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(
						"jdbc:sqlserver:// 128.237.138.192:1433;databaseName=WIFI_DB", "Krishna",
						"sql123");
				System.out.println("connection established!!");

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return connection;
	}
	

}