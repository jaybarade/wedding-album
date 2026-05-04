package com.wedding.backend.service;

import com.wedding.backend.dto.PhotoResponse;
import com.wedding.backend.dto.WeddingResponse;
import com.wedding.backend.entity.User;
import com.wedding.backend.entity.Wedding;
import com.wedding.backend.repository.UserRepository;
import com.wedding.backend.repository.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WeddingService {
    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Wedding createWedding(String title, MultipartFile coverImage) throws IOException {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email).orElseThrow();

        String imageUrl = null;
        if (coverImage != null && !coverImage.isEmpty()) {
            imageUrl = cloudinaryService.uploadImage(coverImage);
        }

        String slug = generateUniqueSlug(title);

        Wedding wedding = Wedding.builder()
                .title(title)
                .slug(slug)
                .coverImage(imageUrl)
                .user(user)
                .build();

        return weddingRepository.save(wedding);
    }

    public List<WeddingResponse> getMyWeddings() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email).orElseThrow();

        return weddingRepository.findByUser(user).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public WeddingResponse getWeddingBySlug(String slug) {
        Wedding wedding = weddingRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Wedding not found with slug: " + slug));
        return mapToResponse(wedding);
    }

    private String generateUniqueSlug(String title) {
        String baseSlug = title.toLowerCase().replaceAll("[^a-z0-9]", "-");
        String slug = baseSlug;
        int count = 1;
        while (weddingRepository.existsBySlug(slug)) {
            slug = baseSlug + "-" + count++;
        }
        return slug;
    }

    private WeddingResponse mapToResponse(Wedding wedding) {
        return WeddingResponse.builder()
                .id(wedding.getId())
                .title(wedding.getTitle())
                .slug(wedding.getSlug())
                .coverImage(wedding.getCoverImage())
                .createdAt(wedding.getCreatedAt())
                .photos(wedding.getPhotos() != null ? wedding.getPhotos().stream()
                        .map(photo -> PhotoResponse.builder()
                                .id(photo.getId())
                                .imageUrl(photo.getImageUrl())
                                .uploadedAt(photo.getUploadedAt())
                                .build())
                        .collect(Collectors.toList()) : List.of())
                .build();
    }
}
