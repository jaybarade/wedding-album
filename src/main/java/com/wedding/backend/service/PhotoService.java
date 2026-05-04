package com.wedding.backend.service;

import com.wedding.backend.dto.PhotoResponse;
import com.wedding.backend.entity.Photo;
import com.wedding.backend.entity.Wedding;
import com.wedding.backend.repository.PhotoRepository;
import com.wedding.backend.repository.WeddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wedding.backend.dto.UploadResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public PhotoResponse uploadPhoto(Long weddingId, MultipartFile file) throws IOException {
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new RuntimeException("Wedding not found"));

        String folder = "weddings/" + weddingId;
        String imageUrl = cloudinaryService.uploadImage(file, folder);

        Photo photo = Photo.builder()
                .wedding(wedding)
                .imageUrl(imageUrl)
                .build();

        photo = photoRepository.save(photo);

        return PhotoResponse.builder()
                .id(photo.getId())
                .imageUrl(photo.getImageUrl())
                .uploadedAt(photo.getUploadedAt())
                .build();
    }

    public UploadResponse uploadMultiplePhotos(Long weddingId, MultipartFile[] files) {
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new RuntimeException("Wedding not found"));

        String folder = "weddings/" + weddingId;

        List<CompletableFuture<String>> futures = Arrays.stream(files)
                .map(file -> CompletableFuture.supplyAsync(() -> {
                    try {
                        String imageUrl = cloudinaryService.uploadImage(file, folder);
                        Photo photo = Photo.builder()
                                .wedding(wedding)
                                .imageUrl(imageUrl)
                                .build();
                        photoRepository.save(photo);
                        return imageUrl;
                    } catch (Exception e) {
                        System.err.println("Failed to upload file: " + file.getOriginalFilename() + " - " + e.getMessage());
                        return null; // Skip failed uploads
                    }
                }))
                .collect(Collectors.toList());

        List<String> successfulUrls = futures.stream()
                .map(CompletableFuture::join)
                .filter(url -> url != null)
                .collect(Collectors.toList());

        return UploadResponse.builder()
                .status(true)
                .message("Upload completed. Successfully uploaded " + successfulUrls.size() + " out of " + files.length + " files.")
                .data(successfulUrls)
                .build();
    }

    public List<PhotoResponse> getPhotosByWedding(Long weddingId) {
        return photoRepository.findByWeddingId(weddingId).stream()
                .map(photo -> PhotoResponse.builder()
                        .id(photo.getId())
                        .imageUrl(photo.getImageUrl())
                        .uploadedAt(photo.getUploadedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
