package com.hhub.repo.test;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
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
	
	private User user;
	
	@Before
	public void createUser() {
		
		Role role = new Role("TEST_ROLE");
		entityManager.persist(role);
		
	    user = new User("alex", "perera", "test_password", "test@gmail.com", role, true);
	    entityManager.persist(user);
	    entityManager.flush();
	    
	}
	
	@Test
	public void whenUpdateBlogPostSetStatusById_thenReturnInt() {
		
	    // given
	    BlogPost blogPost = new BlogPost("title", "content", "image/path", user, BlogStatus.READY, new Date(), "createdBy", new Date(), "modifyBy", new Date() );
	    entityManager.persist(blogPost);
	    entityManager.flush();
	    
	    // when
	    int founBlogPost = blogPostRepository.updateBlogPostSetStatusById(BlogStatus.READY, blogPost.getId());
	 
	    // then
	    assertEquals(founBlogPost, blogPost.getId());
	}
	
	@Test
	public void whenUpdateBlogPostSetStatusDateById_thenReturnInt() {
		
	    // given	    
	    BlogPost blogPost = new BlogPost("title", "content", "image/path", user, BlogStatus.READY, null, "createdBy", new Date(), "modifyBy", new Date() );
	    entityManager.persist(blogPost);
	    entityManager.flush();
	    
	    // when
	    int founBlogPost = blogPostRepository.updateBlogPostSetStatusDateById(BlogStatus.READY, blogPost.getId(), new Date());
	 
	    // then
	    assertEquals(founBlogPost, blogPost.getId());
	}
	
	@Test
	public void whenArchiveBlogPost_thenReturnInt() {
		
		Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        
	    // given	    
	    BlogPost blogPost = new BlogPost("title", "content", "image/path", user, BlogStatus.READY, yesterday, "createdBy", new Date(), "modifyBy", new Date() );
	    entityManager.persist(blogPost);
	    entityManager.flush();
	    
	    // when
	    blogPostRepository.archiveBlogPost(BlogStatus.ARCHIVED, new Date());
	    Optional<BlogPost> foundBlogPost =  blogPostRepository.findById(blogPost.getId());
	 
	    // then
	   // assertEquals(foundBlogPost.get(), notNullValue());
	    assertEquals(foundBlogPost.get(), blogPost);
	}

}
