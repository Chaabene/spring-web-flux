package com.quick.start.service;

import java.util.UUID;

import com.quick.start.domain.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	Mono<User> findById(UUID id);
	Flux<User> findAll();
	Mono<User> saveUser(User user);
	Mono<Void> deleteUserById(UUID id);

	Mono<User> updateUser(User user);

}
