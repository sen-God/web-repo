package main.java.com.example.model;

import java.sql.Timestamp;

public class News {
    private int id;
    private String title;
    private String content;
    private String category;
    private Timestamp createTime;

    public News(int id, String title, String content, String category, Timestamp createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createTime = createTime;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCategory() { return category; }
    public Timestamp getCreateTime() { return createTime; }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
