package com.noisywean.wificlients;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IpDAO {
	
	Connection connection = LocalDBConnectionPool.getConnection();
	
	public boolean NewEntry(String ip,String mac){
				 
		try {
			
			PreparedStatement ps=connection.prepareStatement("insert into IP_TABLE values(?,?)");
			ps.setString(1, ip);
			ps.setString(2, mac);
		
			int rows= ps.executeUpdate();
		
			if(rows > 0)
				return true;
			else
				return false;
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}