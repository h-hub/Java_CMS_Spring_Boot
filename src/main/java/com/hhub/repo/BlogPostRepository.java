package com.hhub.repo;

import org.springframework.data.repository.CrudRepository;
import com.hhub.model.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {

}
