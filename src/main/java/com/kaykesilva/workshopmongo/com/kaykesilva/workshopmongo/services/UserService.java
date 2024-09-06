package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.User;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
}
