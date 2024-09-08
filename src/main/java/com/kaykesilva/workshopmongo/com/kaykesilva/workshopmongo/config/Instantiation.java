package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.Post;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.User;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.dto.CommentDTO;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.dto.authorDTO;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository.PostRepository;
import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
	
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null, fmt.parse("21/03/2018"), "Partiu Viajem!", "Vou viajar pra São Paulo. Abraços!", new authorDTO(alex));
		Post post2 = new Post(null, fmt.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new authorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, mano!", fmt.parse("21/03/2024"), new authorDTO(bob));
		CommentDTO c2 = new CommentDTO("Aproveite", fmt.parse("21/03/2024"), new authorDTO(maria));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", fmt.parse("21/03/2024"), new authorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
		
		
	}
}
