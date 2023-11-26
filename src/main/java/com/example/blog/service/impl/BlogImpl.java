package com.example.blog.service.impl;

import com.example.blog.entity.Blog;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogImpl implements BlogService {
    private final BlogRepository blogRepository;
    @Autowired
    public BlogImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }
}
