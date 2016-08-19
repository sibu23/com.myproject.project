package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	public static Connection con=null;
	
public static Connection getConnection() {
	if(con != null)
		return con;
	else{
		try{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/angular_js";
		String user="root";
		String password="root";
		Class.forName(driver);
        con = DriverManager.getConnection(url, user, password);
	}
		catch(ClassNotFoundException e){
		 e.printStackTrace();}
		catch(SQLException e){
		 e.printStackTrace();}
		
		return con;

}}

}

