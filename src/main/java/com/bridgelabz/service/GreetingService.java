package com.bridgelabz.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.model.User;
import com.bridgelabz.repository.GreetingRepository;

@Service
public class GreetingService implements IGreetingService 
{

	private static String template="Hello, %s!";
	private final AtomicLong counter=new AtomicLong();
	
	@Autowired
	private GreetingRepository greetingRepository;
	@Override
	public Greeting addGreeting(User user) 
	{
		String message=String.format(template,(user.toString().isEmpty())?"Hello World":user.toString());
		return greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
	}

	@Override
	public Greeting getGreetingById(long id) 
	{
		return greetingRepository.getById(id);
	}
	
	public List<Greeting> getAllGreetings() 
	{
		return greetingRepository.findAll();
	}
	
	@Override
	public Greeting editGreeting(long id, User user) 
	{
		String message=String.format(template,(user.toString().isEmpty())?"Hello World":user.toString());
		Optional<Greeting> greetingData  = greetingRepository.findById(id);
		if(greetingData.isPresent()) 
		{
			Greeting greeting=greetingData.get();
			greeting.setMessage(message);
			greetingRepository.save(greeting);
			return greeting;
		}
		return null;
	}

	@Override
	public ResponseEntity<HttpStatus> deleteGreeting(long id) 
	{
		greetingRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}