package com.bridgelabz.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.model.User;

@Service
public interface IGreetingService 
{
	Greeting addGreeting(User user);
	Greeting getGreetingById(long id);
	List<Greeting> getAllGreetings();
	Greeting editGreeting(long id,User user);
	ResponseEntity<HttpStatus> deleteGreeting(long id);
}	