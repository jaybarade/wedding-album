package com.wedding.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WeddingResponse {
    private Long id;
    private String title;
    private String slug;
    private String coverImage;
    private LocalDateTime createdAt;
    private List<PhotoResponse> photos;

    public WeddingResponse() {}

    public WeddingResponse(Long id, String title, String slug, String coverImage, LocalDateTime createdAt, List<PhotoResponse> photos) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.coverImage = coverImage;
        this.createdAt = createdAt;
        this.photos = photos;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<PhotoResponse> getPhotos() { return photos; }
    public void setPhotos(List<PhotoResponse> photos) { this.photos = photos; }

    public static WeddingResponseBuilder builder() {
        return new WeddingResponseBuilder();
    }

    public static class WeddingResponseBuilder {
        private Long id;
        private String title;
        private String slug;
        private String coverImage;
        private LocalDateTime createdAt;
        private List<PhotoResponse> photos;

        public WeddingResponseBuilder id(Long id) { this.id = id; return this; }
        public WeddingResponseBuilder title(String title) { this.title = title; return this; }
        public WeddingResponseBuilder slug(String slug) { this.slug = slug; return this; }
        public WeddingResponseBuilder coverImage(String coverImage) { this.coverImage = coverImage; return this; }
        public WeddingResponseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public WeddingResponseBuilder photos(List<PhotoResponse> photos) { this.photos = photos; return this; }
        public WeddingResponse build() {
            return new WeddingResponse(id, title, slug, coverImage, createdAt, photos);
        }
    }
}
