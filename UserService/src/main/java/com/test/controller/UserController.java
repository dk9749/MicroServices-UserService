package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.User;
import com.test.serviceimpl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl serviceImpl;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User saveUser = serviceImpl.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
	}

	int count = 1;

	@GetMapping("/{id}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//	@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "ratingHotelLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String id) {
		System.out.println("Retry Count----------------------" + count++);
		User user = serviceImpl.getUser(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	public ResponseEntity<User> ratingHotelFallback(String id, Exception exception) {
		exception.printStackTrace();
		return ResponseEntity
				.ok(new User("dummy", "dummy", "dummy@gmail.com", "this is generated when server is down", null));
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = serviceImpl.getAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(allUser);
	}
}
