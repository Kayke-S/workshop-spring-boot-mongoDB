package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);
	//query method
	//Sao metodos predefinidos que basta chamar pelo nome que o spring.data 
	// faz o resto
	
	//ignoreCase eh uma 'assinatura' que ignora pesquisas maiusculas ou minusculas na url
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
}
