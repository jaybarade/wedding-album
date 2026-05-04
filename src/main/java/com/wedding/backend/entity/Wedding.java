package com.wedding.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "weddings", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug")
})
public class Wedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank
    private String title;

    @NotBlank
    private String slug;

    private String coverImage;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "wedding", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photos;

    public Wedding() {}

    public Wedding(String title, String slug, String coverImage, User user) {
        this.title = title;
        this.slug = slug;
        this.coverImage = coverImage;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Photo> getPhotos() { return photos; }
    public void setPhotos(List<Photo> photos) { this.photos = photos; }

    public static WeddingBuilder builder() {
        return new WeddingBuilder();
    }

    public static class WeddingBuilder {
        private String title;
        private String slug;
        private String coverImage;
        private User user;

        public WeddingBuilder title(String title) { this.title = title; return this; }
        public WeddingBuilder slug(String slug) { this.slug = slug; return this; }
        public WeddingBuilder coverImage(String coverImage) { this.coverImage = coverImage; return this; }
        public WeddingBuilder user(User user) { this.user = user; return this; }
        public Wedding build() {
            return new Wedding(title, slug, coverImage, user);
        }
    }
}
