package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.User;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.dto.UserDTO;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.exception.ObjectNotFoundException;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}

	public User insert(User user) {
		return repository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(User obj) {

		User newUser = findById(obj.getId()); // retorna o usuario ja existente no banco
	    updateData(newUser, obj);
		return repository.save(newUser); // salva novamente o objeto ao banco
	}

	public void updateData(User newUser, User obj) {
		newUser.setName(obj.getName());
        newUser.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
