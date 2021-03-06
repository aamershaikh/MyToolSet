package com.aamer.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aamer.rest.webservices.restfulwebservices.bean.User;
import com.aamer.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.aamer.rest.webservices.restfulwebservices.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// getAllUsers
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userService.getAllUsers();
	}

	// getOneUsers
	// error handling if user is not found, it should return a 404 http status
	// code
	// Implement HATEOAS to include the hyper link to the getOneUser response
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getOneUser(@PathVariable int id) {
		User user = userService.getOneUser(id);
		if (user == null) {
			throw new UserNotFoundException("Not Found");
		}
		
		// to add the hyperlink to get details of all users in the response for 
		// one user we include the link in the response.
		
		EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		return model;
	}

	// saveUser and return a response entity as CREATED
	// Normal response of save operation is 200. It is a good practice to return
	// 201 (CREATED) when a new resource is added.
	@PostMapping(path = "/users")
	public ResponseEntity<User> getOneUser(@Valid @RequestBody User user) {

		// save the user
		User savedUser = userService.addUser(user);

		// to return a response entity as CREATED
		URI location = ServletUriComponentsBuilder.fromCurrentRequest(). // to
																			// current
																			// request
				path("/{id}"). // add {id} and
				buildAndExpand(savedUser.getUserid()). // append the userId that
														// is returned as a
														// response
				toUri(); // to URI

		return ResponseEntity.created(location).build();

	}

	// deleteOneUsers
	// error handling if user is not found, it should return a 404 http status
	// code
	@DeleteMapping(path = "/users/{id}")
	public void deleteOneUser(@PathVariable int id) {
		User user = userService.deleteOneUser(id);
		if (user == null) {
			throw new UserNotFoundException("Not Found");
		}
	}

}
