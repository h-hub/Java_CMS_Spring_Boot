package com.hhub.controlller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.hhub.model.BlogPost;
import com.hhub.model.Role;
import com.hhub.model.User;
import com.hhub.model.dto.BlogDto;
import com.hhub.model.dto.UserDto;
import com.hhub.model.validators.CheckDateFormat;
import com.hhub.repo.BlogPostRepository;
import com.hhub.service.BlogPostService;
import com.hhub.service.UserService;
import com.hhub.util.BlogStatus;

@Controller
public class BlogController {

	private static final String BLOG_IMAGES = "blogImages";
	private static final String TOMCAT_HOME_PROPERTY = "catalina.home";
	private static final String TOMCAT_HOME_PATH = System.getProperty(TOMCAT_HOME_PROPERTY);
	private static final String BLOG_IMAGES_PATH = TOMCAT_HOME_PATH + File.separator + BLOG_IMAGES;

	private static final File BLOG_IMAGES_DIR = new File(BLOG_IMAGES_PATH);
	private static final String BLOG_IMAGES_DIR_ABSOLUTE_PATH = BLOG_IMAGES_DIR.getAbsolutePath() + File.separator;

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
		
		if (blogDto.getStatus()==BlogStatus.READY) {
			m.addAttribute("globalError", "Unable to save");
			m.addAttribute("blogDto", blogDto);
			return "blog/add_blog_post";
		}

		if (result.hasErrors()) {
			m.addAttribute("globalError", "Please check the form for errors");
			m.addAttribute("blogDto", blogDto);
			return "blog/add_blog_post";

		}
		
		MultipartFile file = blogDto.getImage();
		String imagePath = "";

		if (!file.isEmpty()) {

			createBlogImagesDirIfNeeded();

			imagePath = createImage(
					(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())) + file.getOriginalFilename(),
					file);
		}
		
		BlogPost blogPost = createBlogPost(blogDto, imagePath);

		if (blogDto.isPreview()) {
			return "redirect:preview_blog?blogId=" + blogPost.getId();
		} else if (blogDto.isSave()) {
			blogDto = createDtotFromBlogPost(blogPost);
			m.addAttribute("message", "Draft was saved successfully");
			m.addAttribute("blogDto", blogDto);
		} else {
			blogDto = createDtotFromBlogPost(blogPost);
			m.addAttribute("message", "Blog post is ready to be published after review.");
			m.addAttribute("blogDto", blogDto);
		}

		return "blog/add_blog_post";

	}

	private String createImage(String name, MultipartFile file) throws Exception {
		try {
			File image = new File(BLOG_IMAGES_DIR_ABSOLUTE_PATH + name);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image));
			stream.write(file.getBytes());
			stream.close();

			return name;
		} catch (Exception e) {
			throw e;
		}
	}

	@GetMapping("/image/{imageName}")
	public @ResponseBody byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
		createBlogImagesDirIfNeeded();

		File serverFile = new File(BLOG_IMAGES_DIR_ABSOLUTE_PATH + imageName);

		return Files.readAllBytes(serverFile.toPath());
	}

	private void createBlogImagesDirIfNeeded() {
		if (!BLOG_IMAGES_DIR.exists()) {
			BLOG_IMAGES_DIR.mkdirs();
		}
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
	public String previewBlog(@RequestParam("blogId") Integer blogId, Model m) throws Exception {

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

		BlogDto blogDto = new BlogDto();

		blogDto.setId(blogPost.getId());
		blogDto.setTitle(blogPost.getTitle());
		blogDto.setContent(blogPost.getContent());
		blogDto.setImagePath(blogPost.getImage());
		blogDto.setStatus(blogPost.getBlogStatus());

		return blogDto;

	}

	@GetMapping("/edit_blog")
	public String editBlog(@RequestParam("blogId") Integer blogId, Model m) throws Exception {

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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        Iterable<BlogPost> blogPostList;
        
        if(user.getRole().getName().equals("EDITOR")) {
        	blogPostList = user.getPosts();
        } else {
        	blogPostList = blogPostService.getAll();
        }
		
		model.addAttribute("blogPostList", blogPostList);
		return "blog/blog_post_list";
	}

	@PostMapping("/change_blog_post_status")
	public String changeBlogStatus(@RequestParam("blogPostId") Integer blogPostId,
			@RequestParam("approve") Boolean approve, @RequestParam("pubToDate") String date) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		BlogStatus status;

		if (approve) {
			status = BlogStatus.PUBLISHED;
		} else {
			status = BlogStatus.REJECT;
		}

		if (date.isEmpty()) {
			blogPostService.changeStatus(blogPostId, status);
		} else {
			blogPostService.changeStatus(blogPostId, status, formatter.parse(date));
		}

		return "redirect:blog_post_list";
	}

}
