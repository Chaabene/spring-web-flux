package com.quick.start.style.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.quick.start.domain.User;
import com.quick.start.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{id}")
	public Mono<User> getUserById(@PathVariable("id") UUID id){
		return userService.findById(id);
	}
	
	@GetMapping("/users")
	public Flux<User> getUserById(){
		return userService.findAll();
	}

}
