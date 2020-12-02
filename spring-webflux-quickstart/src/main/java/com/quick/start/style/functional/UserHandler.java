package com.quick.start.style.functional;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.quick.start.domain.User;
import com.quick.start.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
	@Autowired
	private UserService userService;
	
	public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.findAll(),User.class);
	}
	
	public Mono<ServerResponse> getUserById(ServerRequest serverRequest){
		String id = serverRequest.pathVariable("id");
		return 
				userService.findById(UUID.fromString(id))
				.flatMap(user-> {
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(user),User.class);
				}).switchIfEmpty(ServerResponse.notFound().build());
	}
	public Mono<ServerResponse> createUser(ServerRequest serverRequest){
		Mono<User> userToSave = serverRequest.bodyToMono(User.class);
		return userToSave.flatMap(user-> ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(userService.saveUser(user),User.class));
	}
	
	public Mono<ServerResponse> deleteUserById(ServerRequest serverRequest){
		String id = serverRequest.pathVariable("id");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.deleteUserById(UUID.fromString(id)),User.class);
	}
	
	public Mono<ServerResponse> updateUser(ServerRequest serverRequest){
		Mono<User> userToUpdate = serverRequest.bodyToMono(User.class);
		return userToUpdate.flatMap(user-> userService.updateUser(user))
						   .flatMap(u->{
							   return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body( Mono.just(u),User.class);
						   })
						   .switchIfEmpty(ServerResponse.notFound().build());
			
	}		

}
