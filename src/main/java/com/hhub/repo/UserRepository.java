package com.hhub.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hhub.model.Book;
import com.hhub.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	

}
