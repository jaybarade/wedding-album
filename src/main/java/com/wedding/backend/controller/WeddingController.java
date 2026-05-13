package com.wedding.backend.controller;

import com.wedding.backend.dto.WeddingResponse;
import com.wedding.backend.entity.Wedding;
import com.wedding.backend.service.WeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class WeddingController {
    @Autowired
    private WeddingService weddingService;

    // Private Endpoints
    @PostMapping("/weddings")
    public ResponseEntity<?> createWedding(
            @RequestParam("title") String title,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage) throws IOException {
        WeddingResponse weddingResponse = weddingService.createWedding(title, coverImage);
        return ResponseEntity.ok(weddingResponse);
    }

    @GetMapping("/weddings/my")
    public ResponseEntity<List<WeddingResponse>> getMyWeddings() {
        return ResponseEntity.ok(weddingService.getMyWeddings());
    }

    // Public Endpoint
    @GetMapping("/public/weddings/{slug}")
    public ResponseEntity<WeddingResponse> getWeddingBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(weddingService.getWeddingBySlug(slug));
    }
}
