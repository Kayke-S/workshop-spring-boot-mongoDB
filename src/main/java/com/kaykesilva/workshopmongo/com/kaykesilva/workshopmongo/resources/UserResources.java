package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.Post;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.User;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.dto.UserDTO;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.services.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDTO = new ArrayList<>();
		
		for(User obj: list) {
		    UserDTO instantiationDto = new UserDTO(obj);
		    listDTO.add(instantiationDto);
		}
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj); // esse retorno é importante para que o banco de dados atualize o objeto, atribuindo um novo valor ao id. 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build(); // codigo 204 
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		User newObj = service.fromDTO(objDTO);
		newObj.setId(id);
		newObj = service.update(newObj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value ="/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
	
	
}
