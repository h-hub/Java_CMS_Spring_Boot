package com.hhub.repo.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.hhub.model.BlogPost;
import com.hhub.model.Role;
import com.hhub.model.User;
import com.hhub.repo.BlogPostRepository;
import com.hhub.util.BlogStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BlogRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	@Test
	public void whenUpdateBlogPostSetStatusById_thenReturnInt() {
	    // given
		Role role = new Role("TEST_ROLE");
		entityManager.persist(role);
		
	    User alex = new User("alex", "perera", "test_password", "test@gmail.com", role, true);
	    entityManager.persist(alex);
	    entityManager.flush();
	    
	    BlogPost blogPost = new BlogPost("title", "content", "image/path", alex, BlogStatus.READY, new Date(), "createdBy", new Date(), "modifyBy", new Date() );
	 
	    // when
	    int founBlogPost = blogPostRepository.updateBlogPostSetStatusById(BlogStatus.READY, blogPost.getId());
	 
	    // then
	    assertThat(founBlogPost, is(blogPost.getId()));
	}

}
