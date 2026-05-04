package com.wedding.backend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wedding_id", nullable = false)
    private Wedding wedding;

    @Column(nullable = false)
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    public Photo() {}

    public Photo(Wedding wedding, String imageUrl) {
        this.wedding = wedding;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Wedding getWedding() { return wedding; }
    public void setWedding(Wedding wedding) { this.wedding = wedding; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public static PhotoBuilder builder() {
        return new PhotoBuilder();
    }

    public static class PhotoBuilder {
        private Wedding wedding;
        private String imageUrl;

        public PhotoBuilder wedding(Wedding wedding) { this.wedding = wedding; return this; }
        public PhotoBuilder imageUrl(String imageUrl) { this.imageUrl = imageUrl; return this; }
        public Photo build() {
            return new Photo(wedding, imageUrl);
        }
    }
}
