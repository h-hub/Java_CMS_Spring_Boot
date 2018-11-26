package com.hhub.repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hhub.model.BlogPost;
import com.hhub.util.BlogStatus;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {

    @Modifying
    @Transactional
    @Query("update BlogPost p set p.blogStatus = :status where p.id = :blogPostId")
    int updateBlogPostSetStatusById(@Param("status") BlogStatus status, @Param("blogPostId") Integer blogPostId);

    @Modifying
    @Transactional
    @Query("update BlogPost p set p.blogStatus = :status, p.publishedToDate = :pubToDate  where p.id = :blogPostId")
    int updateBlogPostSetStatusDateById(@Param("status") BlogStatus status, @Param("blogPostId") Integer blogPostId,
	    @Param("pubToDate") Date pubToDate);

    @Modifying
    @Transactional
    @Query("update BlogPost p set p.blogStatus = :status where p.publishedToDate < :today")
    void archiveBlogPost(@Param("status") BlogStatus status, @Param("today") Date today);

    List<BlogPost> findByOrderByIdDesc();

}
