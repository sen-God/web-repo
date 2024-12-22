package com.example.advertising.model;

import java.util.Arrays;

public class Advertisement {
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private int id;
    private String title;
    private String content;
    private String[] tags;
    private String imageUrl; // 新增广告图片链接属性，用于存储广告图片地址

    public Advertisement(int id, String title, String content, String[] tags, String imageUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String[] getTags() {
        return tags;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}