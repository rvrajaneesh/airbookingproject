package com.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.userinfo.*;
import com.user.exception.*;

import com.user.services.*;

@RestController
@RequestMapping("/")
public class Controller {
	
	@Autowired
	private Services services;

	@GetMapping("/users")
	public List<User> getUsers() {
		return services.getUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity <User> getUserById(@PathVariable(value = "id") String userId)
	throws ResourceNotFoundException{
		User user = services.getUser(userId);
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity <User> updateFlightById(@PathVariable(value = "id") String userId, 
			@RequestBody User user) throws ResourceNotFoundException {
			
			User updatedUser = services.updateUser(userId, user);
			return ResponseEntity.ok().body(updatedUser);
	}

	@CrossOrigin
	@PostMapping("/users")
	public void addFlight( @RequestBody User user) {
		//flight.setDate(new Date());
		services.addUser(user);
	}
	
	
	
	@DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteFligh(@PathVariable(value = "id") String userId)
    throws ResourceNotFoundException {
        services.deleteUser(userId);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
