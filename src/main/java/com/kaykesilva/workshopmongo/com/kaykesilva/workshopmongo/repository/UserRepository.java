package com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kaykesilva.workshopmongo.com.kaykesilva.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
