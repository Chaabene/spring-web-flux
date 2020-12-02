package com.quick.start;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import com.quick.start.domain.User;
import com.quick.start.subscriber.BackPressureSubscriber;

@SpringBootApplication
public class SpringWebfluxClientQuickstartApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxClientQuickstartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		WebClient webClient= WebClient.create("http://localhost:8080");
	    User userToCreate= new User(UUID.randomUUID(), "First Name", "Last Name");
		
		
		webClient.post().uri("/functional-style/users")
		.bodyValue(userToCreate)
		.exchange().ofType(User.class)
		.subscribe(System.out::println);
		
		List<UUID> users= webClient.get().uri("/functional-style/users")
					   .retrieve()
					   .bodyToFlux(User.class)
					   .map(user->user.getId())
					   .log().collectList().block();
		
		Iterator<UUID> iterator = users.iterator();
		while (iterator.hasNext()) {
			webClient.get().uri("/functional-style/users/{id}",iterator.next())  
			   .retrieve()
			   .bodyToMono(User.class)
			   .log().subscribe(System.out::println);
		}
		
		
		
		webClient.get().uri("/functional-style/users")
		.retrieve()
		.bodyToFlux(User.class)
		.log()
		.subscribe(System.out::println);
		
		System.out.println("===========>>>>>" + Thread.currentThread());
		BackPressureSubscriber<User> backpressureReadySubscriber = new BackPressureSubscriber<>();
		webClient.get().uri("/functional-style/users")
				.retrieve()
				.bodyToFlux(User.class)
				.log()
				.subscribe(backpressureReadySubscriber);
		System.out.println("===========>>>>>" + Thread.currentThread());
	}

}
