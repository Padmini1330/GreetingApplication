package com.bridgelabz.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.model.User;


@Service
public class GreetingService implements IGreetingService 
{

	private static String template="Hello, %s!";
	private final AtomicLong counter=new AtomicLong();
	
	@Override
	public Greeting addGreeting(User user) 
	{
		String message=String.format(template,(user.toString().isEmpty())?"Hello World":user.toString());
		return new Greeting(counter.incrementAndGet(), message);
	}

	@Override
	public Greeting getGreetingById(long id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}