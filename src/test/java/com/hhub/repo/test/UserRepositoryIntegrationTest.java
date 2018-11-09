package com.hhub.repo.test;


import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;

import com.hhub.model.Role;
import com.hhub.model.User;
import com.hhub.repo.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void whenUpdateUserSetStatusById_thenReturnInt() {
	    // given
		Role role = new Role("TEST_ROLE");
		entityManager.persist(role);
		
	    User alex = new User("alex", "perera", "test_password", "test@gmail.com", role, true);
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    // when
	    int founduserId = userRepository.updateUserSetStatusById(false, alex.getId());
	 
	    // then
	    assertThat(founduserId, is(alex.getId()));
	}
	
	@Test
	public void whenfindByEmail_thenReturnUser() {
	    // given
		Role role = new Role("TEST_ROLE");
		entityManager.persist(role);
		
	    User alex = new User("alex", "perera", "test_password", "test@gmail.com", role, true);
	    entityManager.persist(alex);
	    entityManager.flush();
	 
	    // when
	    User user = userRepository.findByEmail("test@gmail.com");
	 
	    // then
	    assertThat(user, notNullValue());
	    assertThat(user, is(alex));
	}

}
