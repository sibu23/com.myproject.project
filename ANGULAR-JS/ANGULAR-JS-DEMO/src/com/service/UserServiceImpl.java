package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.Iterator;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DaoFactory;
import com.model.User;
 
@Service("userService")
@Transactional
public  class UserServiceImpl implements UserService{
	
	Connection con = DaoFactory.getConnection();
	


	   public void isUserExist(User user) 
   	{
  		 try {
  	            PreparedStatement ps = con.prepareStatement("select username from Users where username=?");
  	            ps.setString(1,user.getUsername());
  	            ResultSet rs = ps.executeQuery();
  	            if (rs.next()) // found
  	            {
  	               System.out.println("User already exist..");;
  	            } else {
  	            	saveUser(user);
  	            }
  	        } catch (Exception ex) {
  	            System.out.println("Error in check() -->" + ex.getMessage());
  	        } 
  	}
     
    public void saveUser(User user) {
    	System.out.println("username::"+user.getUsername());
    	try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into Users(username,address,email)values(?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getAddress());
            preparedStatement.setString(3,user.getEmail()); 
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();
        }System.out.println("insert successfully");
    }
 
    public void updateUser(User user) {
    	
    	try {
		 	
            PreparedStatement preparedStatement = con.prepareStatement("update Users set username=?,address=?,email=? where id=?");
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getAddress());		           
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4,user.getId());
            preparedStatement.executeUpdate();
 
        } catch (SQLException e) {
            e.printStackTrace();}

   }

    public void deleteUserById(int id) {
    	try
		{
			PreparedStatement ps=con.prepareStatement("delete from Users where id="+id);
			ps.executeUpdate();
		}catch(SQLException e)
		{
            e.printStackTrace();
        }	
    }
    public String findAllUsers() throws JSONException{
    	JSONArray users = new JSONArray();
    	 try {
    		 
	            Statement statement = con.createStatement();
	            ResultSet rs = statement.executeQuery("select id,username,address,email from Users  order by id desc");
	            while (rs.next()) {
	            	JSONObject obj=new JSONObject();
	            	obj.put("id",rs.getInt(1));
	            	obj.put("username", rs.getString(2));
	            	obj.put("address", rs.getString(3));
	            	obj.put("email", rs.getString(4));
	                users.add(obj);
	            }
	          //  System.out.println("users::"+users.toString());
	        } catch (SQLException e) {
	            e.printStackTrace();
	            }
    	 return users.toString();

    }

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



}
