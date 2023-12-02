package com.example.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name="article")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Size(max = 5000)
    private String description;
    private String home_image;
    private String detail_image;
    private String text;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHome_image() {
        return home_image;
    }

    public void setHome_image(String home_image) {
        this.home_image = home_image;
    }

    public String getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(String detail_image) {
        this.detail_image = detail_image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
