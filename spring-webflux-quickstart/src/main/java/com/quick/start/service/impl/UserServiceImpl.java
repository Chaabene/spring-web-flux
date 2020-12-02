package com.quick.start.service.impl;

import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.start.domain.User;
import com.quick.start.repositories.UserRepository;
import com.quick.start.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Mono<User> findById(UUID id) {
		return userRepository.findById(id);
	}

	@Override
	public Flux<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Mono<User> saveUser(User user) {
		return userRepository.insert(user).delayElement(Duration.ofMillis(3000));
	}

	@Override
	public Mono<Void> deleteUserById(UUID id) {
		return userRepository.deleteById(id);
	}

	@Override
	public Mono<User> updateUser(User user) {
		Mono<User> findById = userRepository.findById(user.getId());
		return findById.flatMap(p -> {
			p.setFirstName(user.getFirstName());
			p.setLastName(user.getLastName());
			return userRepository.insert(p);
		});

	}

}
