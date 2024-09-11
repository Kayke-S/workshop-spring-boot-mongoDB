package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.Post;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.exception.ObjectNotFoundException;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado"));
	}
	
	public List<Post> finByTitleCotaining(String text){
		return repository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
