package com.hhub.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hhub.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Modifying
	@Transactional
    @Query("update User u set u.status = :status where u.id = :id")
    int updateUserSetStatusById(@Param("status") boolean status, @Param("id") Integer id);
	
	User findByEmail(String email);

}
