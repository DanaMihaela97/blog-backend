package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public  ResponseEntity<List<Blog>> getAllBlogs() {
        logger.info("Accessing getAllBlogs method");
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
    @GetMapping("/blogs/{id}")
    public ResponseEntity<Optional<Blog>> getBlogById(@PathVariable Long id) {
        Optional<Blog> blogInfo = blogService.getBlogById(id);
        return ResponseEntity.ok(blogInfo);
    }
    @PostMapping("/blogs")
    public Blog createArticle(@Valid @RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }
    @PutMapping("/blogs/{id}")
    public ResponseEntity<String> updateBlog(@RequestBody Blog blog, @PathVariable Long id) {
        blog.setId(id);
        blogService.updateBlog(blog);
        return ResponseEntity.ok("");
    }
    @DeleteMapping("/blogs/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlogById(id);
    }
}