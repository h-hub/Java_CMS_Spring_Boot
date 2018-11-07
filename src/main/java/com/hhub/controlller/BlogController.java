package com.hhub.controlller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hhub.exception.PostNotFoundException;
import com.hhub.model.BlogPost;
import com.hhub.model.dto.BlogDto;
import com.hhub.service.BlogPostService;
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
			
			if (result.getGlobalError() != null) {
				m.addAttribute("globalError", result.getGlobalError().getDefaultMessage());
			}
			
			m.addAttribute("error", "Please check the form for errors");

			m.addAttribute("blogDto", blogDto);
			return "blog/add_blog_post";

		}
		
		MultipartFile file = blogDto.getImage();
		String imagePath = "";

		if (!file.isEmpty()) {

			createBlogImagesDirIfNeeded();

			imagePath = blogPostService.createImage(
					(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())) + file.getOriginalFilename(),
					file, BLOG_IMAGES_DIR_ABSOLUTE_PATH);
		}
		
		BlogPost blogPost = blogPostService.createBlogPost(blogDto, imagePath);

		if (blogDto.isPreview()) {
			return "redirect:preview_blog?blogId=" + blogPost.getId();
		} else if (blogDto.isSave()) {
			blogDto = createDtoFromBlogPost(blogPost);
			m.addAttribute("message", "Draft was saved successfully");
			m.addAttribute("blogDto", blogDto);
		} else {
			blogDto = createDtoFromBlogPost(blogPost);
			m.addAttribute("message", "Blog post is ready to be published after review.");
			m.addAttribute("blogDto", blogDto);
		}

		return "blog/add_blog_post";

	}

	@GetMapping("/image/{imageName}")
	public @ResponseBody byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
		createBlogImagesDirIfNeeded();

		File serverFile = new File(BLOG_IMAGES_DIR_ABSOLUTE_PATH + imageName);
		File file = ResourceUtils.getFile("classpath:static/image-not-found.png");

		try {
			return Files.readAllBytes(serverFile.toPath());
		} catch (IOException e) {
			return Files.readAllBytes(file.toPath());
		}
		
	}

	private void createBlogImagesDirIfNeeded() {
		if (!BLOG_IMAGES_DIR.exists()) {
			BLOG_IMAGES_DIR.mkdirs();
		}
	}

	@GetMapping("/preview_blog")
	public String previewBlog(@RequestParam("blogId") Integer blogId, Model m) throws PostNotFoundException {

		Optional<BlogPost> optionalBlogPost = blogPostService.findById(blogId);

		if (optionalBlogPost.isPresent()) {

			BlogPost blogPost = optionalBlogPost.get();
			BlogDto blogDto = createDtoFromBlogPost(blogPost);

			m.addAttribute("blogDto", blogDto);

		} else {
			throw new PostNotFoundException("Blog Post not found");
		}

		return "blog/view_blog_post";
	}

	private BlogDto createDtoFromBlogPost(BlogPost blogPost) {

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
			BlogDto blogDto = createDtoFromBlogPost(blogPost);

			m.addAttribute("blogDto", blogDto);

		} else {
			throw new Exception("blog Post not found");
		}

		return "blog/add_blog_post";
	}

	@GetMapping("/blog_post_list")
	public String showPostList(Model model) {
		
        List<BlogPost> blogPostList = blogPostService.getAll();
        model.addAttribute("blogPostList", blogPostList);
		
		return "blog/blog_post_list";
	}

	@PostMapping("/change_blog_post_status")
	public String changeBlogStatus(@RequestParam("blogPostId") Integer blogPostId,
			@RequestParam("approve") Boolean approve, @RequestParam("pubToDate") String date) throws ParseException {

		blogPostService.changeStatus(blogPostId, approve,date);

		return "redirect:blog_post_list";
	}

}
