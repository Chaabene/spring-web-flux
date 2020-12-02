package com.quick.start.style.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

	@Bean
	RouterFunction<ServerResponse> usersRoute(UserHandler userHandler){
		RequestPredicate getAllUserRequestPredicate=GET("/functional-style/users").and(accept(MediaType.APPLICATION_JSON));
		RequestPredicate getUserByIdRequestPredicate=GET("/functional-style/users/{id}").and(accept(MediaType.APPLICATION_JSON));
		RequestPredicate createUserRequestPredicate=POST("/functional-style/users").and(accept(MediaType.APPLICATION_JSON));
		RequestPredicate deleteUserByIdRequestPredicate=DELETE("/functional-style/users/{id}").and(accept(MediaType.APPLICATION_JSON));
		RequestPredicate updateUserRequestPredicate=PUT("/functional-style/users").and(accept(MediaType.APPLICATION_JSON));
		return RouterFunctions.route(getAllUserRequestPredicate,s->userHandler.getAllUsers(s))
							  .andRoute(getUserByIdRequestPredicate,userHandler::getUserById)
							  .andRoute(createUserRequestPredicate, userHandler::createUser)
							  .andRoute(deleteUserByIdRequestPredicate, userHandler::deleteUserById)
							  .andRoute(updateUserRequestPredicate, userHandler::updateUser);
	}
	
}
