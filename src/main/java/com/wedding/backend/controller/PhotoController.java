package com.wedding.backend.controller;

import com.wedding.backend.dto.PhotoResponse;
import com.wedding.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.wedding.backend.dto.UploadResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @PostMapping("/upload")
    public ResponseEntity<PhotoResponse> uploadPhoto(
            @RequestParam("weddingId") Long weddingId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(photoService.uploadPhoto(weddingId, file));
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<UploadResponse> uploadMultiplePhotos(
            @RequestParam("weddingId") Long weddingId,
            @RequestParam("files") MultipartFile[] files) {
        
        // Validation: Only allow image types (jpg, jpeg, png)
        List<String> allowedTypes = Arrays.asList("image/jpeg", "image/jpg", "image/png");

        boolean allImages = Arrays.stream(files).allMatch(file -> {
            String type = file.getContentType();
            return type == null || allowedTypes.contains(type.toLowerCase());
        });

        if (!allImages) {
            return ResponseEntity.badRequest().body(UploadResponse.builder()
                    .status(false)
                    .message("Invalid file type. Only JPG, JPEG, and PNG are allowed.")
                    .build());
        }

        return ResponseEntity.ok(photoService.uploadMultiplePhotos(weddingId, files));
    }

    @GetMapping("/{weddingId}")
    public ResponseEntity<List<PhotoResponse>> getPhotos(@PathVariable Long weddingId) {
        return ResponseEntity.ok(photoService.getPhotosByWedding(weddingId));
    }
}
