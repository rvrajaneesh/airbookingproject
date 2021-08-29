package com.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.*;
import com.user.services.*;


@Controller
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/getList")
	public List<User> getUserList(){
		return service.getUserList();
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		service.saveUser(user);
		return "Added user with id :: " + user.getUserId();		
	}
	
	@GetMapping("/getUser/{id}")
	public User getUserById(@PathVariable(value= "id") Integer id) {
		
		Optional<User> oUser=  service.getUserById(id);
		User u = oUser.get();
		return u;	
	}
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable Integer id , @RequestBody User user) {	
		return service.updateUser(id, user);
	}
	
	@DeleteMapping("/delete/{id}")
	public User deleteUser(@PathVariable Integer id) {
		return service.deleteUser(id);
	}
	
	
	

}