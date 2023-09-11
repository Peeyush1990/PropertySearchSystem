package com.amdocs.propertySearch.dao;

import java.sql.*;

public class DBconnection{
		private static Connection connection;
		public static Connection getConnection() {
			if(connection == null) {
				try {
				Class.forName("oracle.jdbc.driver.OracleDriver"); //registration 
				connection=DriverManager.getConnection("Jdbc:Oracle:thin:@localhost:1521:orcl","scott","tiger"); //connection				
			    } catch(Exception e){
			    	e.printStackTrace();
			    	}
			}		
			return connection;
		}
}