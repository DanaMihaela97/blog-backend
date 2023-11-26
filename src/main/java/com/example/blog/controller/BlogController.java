package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    @Autowired

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Optional<Blog>> getBlogById(@PathVariable Long id){
        Optional<Blog> blogInfo = blogService.getBlogById(id);
        return ResponseEntity.ok(blogInfo);
    }
    @GetMapping("/blogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @PostMapping("/blogs/create")
    public Blog createArticle(@Valid @RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }

    @DeleteMapping("/blogs/delete/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlogById(id);
    }
}
