package com.wedding.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class WeddingRequest {
    @NotBlank
    private String title;

    public WeddingRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
