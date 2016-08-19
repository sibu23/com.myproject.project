package com.service;
 
//import java.util.List;
//import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
//import atg.taglib.json.util.JSONObject;
import com.model.User;
 
 
 
public interface UserService {
     
    User findById(int id);
     
    User findByName(String name);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserById(int id);
 
    String findAllUsers() throws JSONException; 
     
     void isUserExist(User user);
     
}