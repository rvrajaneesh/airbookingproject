package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.*;
import com.user.model.*;
import com.user.services.*;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/getList")
	public List<User> getUserList(){
		return service.getUserList();
	}
	
	@PostMapping("/addUser")
	@ApiOperation(value = "Adds the new User into Database")
	public String addUser(@RequestBody User user) {
		service.saveUser(user);
		return "Added user with id :: " + user.getId();		
	}
	
	@GetMapping("/getUserByEmail/{email}")
	public User getUserByEmail(@PathVariable String email ) {
		return service.getUserByEmail(email);
	}
	
	@GetMapping("/getUser/{id}")
	@ApiOperation(value = "Retrieve the User Details by giving Id",
	notes = "Retrieve the User Details by giving Id")
	public User getUserById(@PathVariable(value= "id") String id) {
		
		Optional<User> oUser=  service.getUserById(id);
		User u = oUser.get();
		return u;	
	}
	@PutMapping("/updateUser/{id}")
	@ApiOperation(value = "Update the User Details by giving Id")
	public User updateUser(@PathVariable String id , @RequestBody User user) {	
		return service.updateUser(id, user);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public User deleteUser(@PathVariable String id) {
		return service.deleteUser(id);
	}
	
	
	

}