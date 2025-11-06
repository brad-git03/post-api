package com.manalese.facebook;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "facebook_posts")
public class FacebookPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Lob
    @Column(nullable = false)
    private String content;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdDateTime;

    @Column(nullable = false)
    private OffsetDateTime modifiedDateTime;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        this.createdDateTime = now;
        this.modifiedDateTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedDateTime = OffsetDateTime.now(ZoneOffset.UTC);
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public OffsetDateTime getCreatedDateTime() { return createdDateTime; }
    // No setter for createdDateTime (set on persist)

    public OffsetDateTime getModifiedDateTime() { return modifiedDateTime; }
    // No setter for modifiedDateTime (set on update)
}
