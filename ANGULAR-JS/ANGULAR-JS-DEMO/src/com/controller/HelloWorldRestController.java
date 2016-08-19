package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


 


import atg.taglib.json.util.JSONException;

import com.model.User;
import com.service.UserService;
  
@RestController
public class HelloWorldRestController {
  
  @Autowired
  UserService userService;  //Service which will do all data retrieval/manipulation work
  
   
    //-------------------Retrieve All Users--------------------------------------------------------
      
    @RequestMapping(value = "/user/", method = RequestMethod.GET )
    public @ResponseBody String listAllUsers()throws JSONException {
        String users = userService.findAllUsers();
       // System.out.println("users::"+users);
        return users;
        
    }

  
    //-------------------Create a User--------------------------------------------------------
      
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public void createuser (@RequestBody User user,UriComponentsBuilder ucBuilder) {
    	 System.out.println("Creating User::" + user.getUsername());
    	userService.isUserExist(user);
    	
    }

    //------------------- Update a User --------------------------------------------------------
      
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
   
        System.out.println("Updating User " + id);
        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
  
   
    //------------------- Delete a User --------------------------------------------------------
      
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting User with id " + id);
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
