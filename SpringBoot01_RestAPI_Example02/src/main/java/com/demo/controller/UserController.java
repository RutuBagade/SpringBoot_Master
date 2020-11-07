package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.ResourceNotFoundException;
import com.demo.model.User;
import com.demo.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository repo;
	
	
	@GetMapping("/users")
	public List<User> getAllUser()
	{
		return this.repo.findAll();
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable (value="id") long userId)
	{
		return this.repo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}
	// create user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return this.repo.save(user);
    }
 // update user
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
        User existingUser = this.repo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.repo.save(existingUser);
    }

    // delete user by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity < User > deleteUser(@PathVariable("id") long userId) {
        User existingUser = this.repo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        this.repo.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
