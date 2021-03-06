package com.bridgelabz.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.model.User;
import com.bridgelabz.service.IGreetingService;

import java.util.List;

@RestController
public class GreetingController 
{
	private static String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private IGreetingService greetingService;

	@PostMapping("/post")
	public Greeting greeting(@RequestParam(value = "firstName", defaultValue = "") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName) 
	{
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return greetingService.addGreeting(user);
	}

	@GetMapping("/get/{id}")
	public String getMessageById(@PathVariable Long id) 
	{
		return greetingService.getGreetingById(id).getMessage();
	}

	@GetMapping("/getAll")
	public List<Greeting> getMessages() 
	{
		return greetingService.getAllGreetings();
	}

	@PutMapping("/edit/{id}")
	public Greeting editMessage(@
			PathVariable Long id,
			@RequestParam(value = "firstName", defaultValue = "") String firstName,
			@RequestParam(value = "lastName", defaultValue = "") String lastName) 
	{
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return greetingService.editGreeting(id, user);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteGreeting(@PathVariable Long id) 
	{
		return greetingService.deleteGreeting(id);
	}
}