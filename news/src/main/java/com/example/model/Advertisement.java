package main.java.com.example.model;

import java.sql.Timestamp;

public class Advertisement {
    private int id;
    private String title;
    private String content;
    private String type;
    private String imageUrl;
    private String sourceSystem;
    private Timestamp createTime;
    private Timestamp expireTime;

    public Advertisement(int id, String title, String content, String type,
                         String imageUrl, String sourceSystem,
                         Timestamp createTime, Timestamp expireTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.imageUrl = imageUrl;
        this.sourceSystem = sourceSystem;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getType() { return type; }
    public String getImageUrl() { return imageUrl; }
    public String getSourceSystem() { return sourceSystem; }
    public Timestamp getCreateTime() { return createTime; }
    public Timestamp getExpireTime() { return expireTime; }
}
