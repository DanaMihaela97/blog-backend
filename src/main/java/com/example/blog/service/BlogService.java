package com.example.blog.service;

import com.example.blog.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> getAllBlogs();
    Optional<Blog> getBlogById(Long id);
    Blog createBlog(Blog blog);
    void deleteBlogById(Long id);

}
