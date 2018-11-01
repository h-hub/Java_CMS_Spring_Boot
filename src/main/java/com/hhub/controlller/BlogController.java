package com.hhub.controlller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.BlogPost;
import com.hhub.model.User;
import com.hhub.model.dto.BlogDto;
import com.hhub.model.dto.UserDto;
import com.hhub.repo.BlogPostRepository;
import com.hhub.repo.BlogPostService;
import com.hhub.service.UserService;
import com.hhub.util.BlogStatus;

@Controller
public class BlogController {

	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private UserService userService;

	@GetMapping("/add_blog_post")
	public String showBlogPostForm(Model model) throws Exception {

		BlogDto blogDto = new BlogDto();
		model.addAttribute("blogDto", blogDto);
		return "blog/add_blog_post";

	}

	@PostMapping("/add_blog_post")
	public String addBlogPost(@ModelAttribute @Valid BlogDto blogDto, BindingResult result, Model m) throws Exception {

		if (result.hasErrors()) {
			m.addAttribute("globalError", "Please check the form for errors");
			m.addAttribute("blogDto", blogDto);
			return "blog/add_blog_post";

		}

		MultipartFile file = blogDto.getImage();

		if (!file.isEmpty()) {

			String imagePath = uploadFile(file);
			BlogPost blogPost = createBlogPost(blogDto, imagePath);
			
			m.addAttribute("message", "Draft was saved successfully");
			m.addAttribute("blogDto", blogDto);
			
			if(blogDto.isPreview()) {
				return "redirect:preview_blog?blogId="+blogPost.getId();
			} else if(blogDto.isSave()) {
				blogDto = createDtotFromBlogPost(blogPost);
				m.addAttribute("message", "Draft was saved successfully");
				m.addAttribute("blogDto", blogDto);
			}

		} else {
			m.addAttribute("globalError", "File is empty");
		}

		return "blog/add_blog_post";

	}

	private String uploadFile(MultipartFile file) throws IOException {

		byte[] bytes = file.getBytes();

		// Creating the directory to store file
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles");
		if (!dir.exists())
			dir.mkdirs();

		// Create the file on server
		File serverFile = new File("/BlogImages" + File.separator
				+ (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())) + file.getOriginalFilename());
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(bytes);
		stream.close();

		System.out.println("You successfully uploaded file=" + serverFile.getAbsolutePath());

		return serverFile.getAbsolutePath();

	}

	private BlogPost createBlogPost(BlogDto blogDto, String imagePath) throws ParseException {
		
		BlogPost blogPost;
		
		Optional<BlogPost> optionalBlogPost = blogPostService.findById(blogDto.getId());
		
		if (optionalBlogPost.isPresent()) {
			blogPost = optionalBlogPost.get();
		} else {
			blogPost = new BlogPost();
		}

		
		blogPost.setTitle(blogDto.getTitle());
		blogPost.setContent(blogDto.getContent());

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		blogPost.setPublishedToDate(formatter.parse(blogDto.getPublishedToDate()));

		blogPost.setImage(imagePath);
		
		if(blogDto.isPreview() || blogDto.isSave()) {
			
			blogPost.setStatus(BlogStatus.DRAFT);
			
		}else {
			
			blogPost.setStatus(BlogStatus.READY);
			
		}

		Optional<User> editor = userService.getUserById(1);

		if (editor.isPresent()) {
			blogPost.setEditor(editor.get());
		} else {
			throw new RuntimeException("User not found");
		}

		blogPost.setCreateBy("email");
		blogPost.setCreateTime(new Date());
		blogPost.setModifyBy("email");
		blogPost.setModifyTime(new Date());

		blogPostService.create(blogPost);

		return blogPost;

	}

	@GetMapping("/add_blog_post_error")
	public String showBlogPostFormError() {

		return "blog/add_blog_post_error";

	}
	
	@GetMapping("/preview_blog")
	public String previewBlog(@RequestParam("blogId") Integer blogId,Model m) throws Exception {
		
		Optional<BlogPost> optionalBlogPost = blogPostService.findById(blogId);
		
		if (optionalBlogPost.isPresent()) {
			
			BlogPost blogPost = optionalBlogPost.get();
			BlogDto blogDto = createDtotFromBlogPost(blogPost);
			
			m.addAttribute("blogDto", blogDto);
			
		} else {
			throw new Exception("blog Post not found");
		}
		
		return "blog/view_blog_post";
	}
	
	private BlogDto createDtotFromBlogPost(BlogPost blogPost) {
		
		BlogDto blogDto = new  BlogDto();
		
		blogDto.setId(blogPost.getId());
		blogDto.setTitle(blogPost.getTitle());
		blogDto.setContent(blogPost.getContent());
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		blogDto.setPublishedToDate(formatter.format(blogPost.getPublishedToDate()));
		
		
		return blogDto;
		
	}
	
	@GetMapping("/edit_blog")
	public String editBlog(@RequestParam("blogId") Integer blogId,Model m) throws Exception {
		
		Optional<BlogPost> optionalBlogPost = blogPostService.findById(blogId);
		
		if (optionalBlogPost.isPresent()) {
			
			BlogPost blogPost = optionalBlogPost.get();
			BlogDto blogDto = createDtotFromBlogPost(blogPost);
			
			m.addAttribute("blogDto", blogDto);
			
		} else {
			throw new Exception("blog Post not found");
		}
		
		return "blog/add_blog_post";
	}
	
	@GetMapping("/blog_post_list")
	public String showPostList(Model model) {
		
		Iterable<BlogPost> blogPostList = blogPostService.getAll();
	    model.addAttribute("blogPostList", blogPostList);
	    return "blog/blog_post_list";
	}

}
