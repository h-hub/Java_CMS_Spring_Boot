package com.hhub.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.BlogPost;
import com.hhub.model.User;
import com.hhub.model.dto.BlogDto;
import com.hhub.repo.BlogPostRepository;
import com.hhub.util.BlogStatus;

@Service
public class BlogPostServiceImpl implements BlogPostService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private BlogPostRepository blogPostRepository;
	
	/* (non-Javadoc)
	 * @see com.hhub.service.IBlogPostService#create(com.hhub.model.BlogPost)
	 */
	@Override
	public BlogPost create(BlogPost blogPost) {
		
        return blogPostRepository.save(blogPost);
        
    }

	/* (non-Javadoc)
	 * @see com.hhub.service.IBlogPostService#findById(java.lang.Integer)
	 */
	@Override
	public Optional<BlogPost> findById(Integer blogId) {

		return blogPostRepository.findById(blogId);

	}

	/* (non-Javadoc)
	 * @see com.hhub.service.IBlogPostService#archiveBlogPost()
	 */
	@Override
	public void archiveBlogPost() {

		blogPostRepository.archiveBlogPost(BlogStatus.ARCHIVED, new Date());
		
	}
	
	@Override
	public String createImage(String name, MultipartFile file, String path) throws Exception {
		try {
			File image = new File(path + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
			stream.write(file.getBytes());
			stream.close();

			return name;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public BlogPost createBlogPost(BlogDto blogDto, String imagePath) throws ParseException {

		BlogPost blogPost;

		Optional<BlogPost> optionalBlogPost = findById(blogDto.getId());

		if (optionalBlogPost.isPresent()) {
			blogPost = optionalBlogPost.get();
		} else {
			blogPost = new BlogPost();
		}

		blogPost.setTitle(blogDto.getTitle());
		blogPost.setContent(blogDto.getContent());
		
		if(!imagePath.isEmpty()) {
			blogPost.setImage(imagePath);
		}

		if (blogDto.isPreview() || blogDto.isSave()) {

			blogPost.setStatus(BlogStatus.DRAFT);

		} else {

			blogPost.setStatus(BlogStatus.READY);

		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        blogPost.setEditor(user);
        
        if (optionalBlogPost.isPresent()) {
    		blogPost.setModifyBy(auth.getName());
    		blogPost.setModifyTime(new Date());
		} else {
			blogPost.setCreateBy(auth.getName());
			blogPost.setCreateTime(new Date());
			blogPost.setModifyBy(auth.getName());
			blogPost.setModifyTime(new Date());
		}
		

		create(blogPost);

		return blogPost;

	}

	@Override
	public void changeStatus(Integer blogPostId, Boolean approve, String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		BlogStatus status;
		
		if (approve) {
			status = BlogStatus.PUBLISHED;
		} else {
			status = BlogStatus.REJECT;
		}

		if (date.isEmpty()) {
			blogPostRepository.updateBlogPostSetStatusById(status, blogPostId);
		} else {
			blogPostRepository.updateBlogPostSetStatusDateById(status, blogPostId, formatter.parse(date));
			
		}
		
	}

	@Override
	public List<BlogPost> getAll() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Iterable<BlogPost> blogPostList;
        List<BlogPost> postsSorted = new ArrayList<BlogPost>();
        
        if(user.getRole().getName().equals("EDITOR")) {
        	Set<BlogPost> posts = user.getPosts();
        	postsSorted = posts.stream().collect(Collectors.toList());
        	Collections.sort(postsSorted, (o1, o2) -> o2.getId()-o1.getId());
        } else {
        	blogPostList = blogPostRepository.findByOrderByIdDesc();
        	blogPostList.forEach(postsSorted::add);
        }
        
        return postsSorted;
        
	}
	
	
}
