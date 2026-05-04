package com.wedding.backend.dto;

import java.time.LocalDateTime;

public class PhotoResponse {
    private Long id;
    private String imageUrl;
    private LocalDateTime uploadedAt;

    public PhotoResponse() {}

    public PhotoResponse(Long id, String imageUrl, LocalDateTime uploadedAt) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.uploadedAt = uploadedAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public static PhotoResponseBuilder builder() {
        return new PhotoResponseBuilder();
    }

    public static class PhotoResponseBuilder {
        private Long id;
        private String imageUrl;
        private LocalDateTime uploadedAt;

        public PhotoResponseBuilder id(Long id) { this.id = id; return this; }
        public PhotoResponseBuilder imageUrl(String imageUrl) { this.imageUrl = imageUrl; return this; }
        public PhotoResponseBuilder uploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; return this; }
        public PhotoResponse build() {
            return new PhotoResponse(id, imageUrl, uploadedAt);
        }
    }
}
